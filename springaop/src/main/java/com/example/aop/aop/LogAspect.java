package com.example.aop.aop;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.aop.annotation.OptLog;
import com.example.aop.dao.ApiLogMapper;
import com.example.aop.dao.OptLogDetailMapper;
import com.example.aop.dao.OptLogOriginalMapper;
import com.example.aop.dao.OptLogSummaryMapper;
import com.example.aop.enums.OperationType;
import com.example.aop.model.OperationLog;
import com.example.aop.model.OptLogDetail;
import com.example.aop.model.OptLogOriginal;
import com.example.aop.model.OptLogSummary;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author fumj
 * @projectName springboot-aop
 * @description: TODO
 * @date 2020/2/1213:50
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private static final String COLUMN = "column";
    private static final String OLD_VALUE = "old_value";
    private static final String NEW_VALUE = "new_value";
    private static final String SUCCCESS_ID = "success_id";

    private final ApiLogMapper apiLogMapper;
    private final OptLogSummaryMapper logSummaryMapper;
    private final OptLogDetailMapper logDetailMapper;
    private final OptLogOriginalMapper logOriginalMapper;

    public LogAspect(ApiLogMapper apiLogMapper, OptLogSummaryMapper logSummaryMapper,
                     OptLogDetailMapper logDetailMapper, OptLogOriginalMapper logOriginalMapper) {
        this.apiLogMapper = apiLogMapper;
        this.logSummaryMapper = logSummaryMapper;
        this.logDetailMapper = logDetailMapper;
        this.logOriginalMapper = logOriginalMapper;
    }

    @Pointcut("@annotation(com.example.aop.annotation.OptLog)")
    public void operationLog() {
    }

    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(StrUtil.repeat("=", 24) + "start around");
        Object res = null;
        long time = System.currentTimeMillis();
        OperationLog operationLog = new OperationLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        OptLog logAnnocation = signature.getMethod().getAnnotation(OptLog.class);
        try {
            if (logAnnocation != null) {
                setUserInfo(operationLog);
                parseAnnotationInfo(logAnnocation, operationLog);
                parseMethodArgsAndValue(joinPoint, operationLog);
                parseRecordBeforeExecuteMethod(logAnnocation, operationLog);
            }
            res = joinPoint.proceed();
            time = System.currentTimeMillis() - time;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //方法执行完成后增加日志
                if (logAnnocation != null && res != null) {
                    int statusCode = ((ResponseEntity) res).getStatusCodeValue();
                    // 操作成功时，查询数据库更新之后的结果
                    if (HttpStatus.OK.value() == statusCode) {
                        parseRecordAfterExecuteMethod(logAnnocation, operationLog, res, time);
                    }
                    saveCompareResultToDataBase(operationLog);

                }
            } catch (Exception e) {
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }

            log.info(StrUtil.repeat("=", 24) + "end around");
        }

        return res;

    }

    /**
     * 保存数据前后比较结果
     *
     * @param operationLog
     */
    private void saveCompareResultToDataBase(OperationLog operationLog) {
        String operationType = operationLog.getOperationType();
        List<OperationLog> logList = new ArrayList<>();
        if (OperationType.INSERT.getValue().equals(operationType)) {
            insertDifferenceField(operationLog);
            logList.add(operationLog);
        } else if (OperationType.UPDATE.getValue().equals(operationType)) {
            updateDifferenceField(operationLog);
            logList.add(operationLog);
        } else if (OperationType.DELETE.getValue().equals(operationType)) {
            // 可能为批量删除
            List<OperationLog> list = deleteDifferenceField(operationLog);
            logList.addAll(list);
        }

        // 解析summaryColumnValue
        parseSummartColumnValue(logList);
        log.debug("{}", StrUtil.repeat("+", 48));
        log.debug(new Gson().toJson(logList));
        log.debug("{}", StrUtil.repeat("+", 48));
        saveOptLog(logList);
    }


    /**
     * 解析summaryColumnValue
     * @param logList
     * @return
     */
    private List<OperationLog> parseSummartColumnValue(List<OperationLog> logList) {
        Map<String,Object> map = null;
        List<OperationLog> copyLogList = new ArrayList<>(logList.size());
        for(OperationLog log : logList){
            if(OperationType.INSERT.getValue().equals(log.getOperationType()) ||
            OperationType.UPDATE.getValue().equals(log.getOperationType())){
                map = log.getAfterOperatorDatabaseRecordList().get(0);
            }else if(OperationType.DELETE.getValue().equals(log.getOperationType())){
                map = log.getBeforeOperatorDatabaseRecordList().get(0);
            }
            log.setSummaryColumnValue(map.get(log.getSummaryColumnName()));
            copyLogList.add(log);
        }
        return copyLogList;
    }

    /**
     * 保存操作日志数据
     * @param logList
     */
    private void saveOptLog(List<OperationLog> logList) {
        long traceId = IdWorker.getId();
        for(OperationLog log : logList){
            OptLogSummary optLog = createOptLogSummary(log);
            optLog.setTraceId(traceId);
            OptLogDetail logDetail = createOptLogDetail(log);
            OptLogOriginal logOriginal = createOptLogOriginal(log);
            logSummaryMapper.insert(optLog);
            logDetail.setOptLogId(optLog.getOptLogId());
            logOriginal.setOptLogId(optLog.getOptLogId());
            logDetailMapper.insert(logDetail);
            logOriginalMapper.insert(logOriginal);
        }
    }

    private OptLogOriginal createOptLogOriginal(OperationLog log) {
        OptLogOriginal logOriginal = new OptLogOriginal();
        logOriginal.setMethod(log.getMethod());
        logOriginal.setColumnName(log.getColumnName());
        logOriginal.setTableName(log.getTableName());
        logOriginal.setColumnValue(new Gson().toJson(log.getColumnValue()));
        logOriginal.setColumnParamterName(log.getColumnParamterName());
        logOriginal.setRequestParamsAndValue(new Gson().toJson(log.getRequestParamsAndValue()));
        logOriginal.setBeforeOperatorData(new Gson().toJson(log.getBeforeOperatorDatabaseRecordList()));
        logOriginal.setAfterOperatorData(new Gson().toJson(log.getAfterOperatorDatabaseRecordList()));
        logOriginal.setReturnValue(log.getReturnValue());
        logOriginal.setSummaryColumnName(log.getSummaryColumnName());
        logOriginal.setSummaryColumnValue(new Gson().toJson(log.getSummaryColumnValue()));
        logOriginal.setCreateTime(LocalDateTime.now());
        return logOriginal;

    }

    private OptLogDetail createOptLogDetail(OperationLog log) {
        OptLogDetail logDetail = new OptLogDetail();
        logDetail.setDiffFieldValue(log.getDifferenceFieldValue());
        return logDetail;


    }

    private OptLogSummary createOptLogSummary(OperationLog log) {
        OptLogSummary optLog = new OptLogSummary();
        optLog.setUserId(Integer.valueOf(log.getUserId()));
        optLog.setUserName(log.getUserName());
        optLog.setIp(log.getIp());
        optLog.setRunTime(log.getRunTime());
        optLog.setOperatorType(log.getOperationType());
        optLog.setOptSummary(getOptSummart(log));
        optLog.setCreateTime(LocalDateTime.now());

        return optLog;
    }

    private String getOptSummart(OperationLog log) {
        StringBuilder builder = new StringBuilder();
        builder.append(getOperatorTypeDesc(log.getOperationType()))
                .append("[")
                .append(log.getSummaryColumnValue())
                .append("]");
        return builder.toString();
    }

    private String getOperatorTypeDesc(String operationType) {
        if(OperationType.DELETE.getValue().equals(operationType)){
            return "删除";
        }else if(OperationType.INSERT.getValue().equals(operationType)){
            return "新增";
        }else if(OperationType.UPDATE.getValue().equals(operationType)){
            return "更新";
        }
        return "未知";
    }

    /**
     * 数据库更新前后字段值比较
     * @param operationLog
     */
    private void updateDifferenceField(OperationLog operationLog) {
        Map<String,Object> oldValueMap = operationLog.getBeforeOperatorDatabaseRecordList().get(0);
        Map<String,Object> newValueMap = operationLog.getAfterOperatorDatabaseRecordList().get(0);
        JSONArray arr = new JSONArray();

        for(String key : oldValueMap.keySet()){
            Object oldValue = oldValueMap.get(key);
            Object newValue = newValueMap.get(key);
            JSONObject object = null;
            if(oldValue == null){
                if(newValue != null){
                    object = new JSONObject();
                    object.put(COLUMN,key);
                    object.put(OLD_VALUE,"");
                    object.put(NEW_VALUE,newValue);
                }
            }else if(!oldValue.equals(newValue)){
                object = new JSONObject();
                object.put(COLUMN,key);
                object.put(OLD_VALUE,oldValue);
                object.put(NEW_VALUE,newValue);
            }
            if(object != null){
                arr.add(object);
            }
        }
        operationLog.setDifferenceFieldValue(arr.toString());
    }

    /**
     * 数据库删除前后字段值比较
     * @param operationLog
     * @return
     */
    private List<OperationLog> deleteDifferenceField(OperationLog operationLog) {
        List<OperationLog> differenceList = new ArrayList<>();
        List<Map<String,Object>> list = operationLog.getBeforeOperatorDatabaseRecordList();
        for(Map<String,Object> item : list){
            List<Map<String,Object>> temp = new ArrayList<>(1);
            temp.add(item);
            OperationLog target = new OperationLog();
            BeanUtils.copyProperties(operationLog,target);
            target.setDifferenceFieldValue(getDeleteDifferenceJSONObject(item).toString());
            target.setBeforeOperatorDatabaseRecordList(temp);
            differenceList.add(target);
        }
        return differenceList;

    }

    /**
     * 数据库插入前后字段值比较
     * @param operationLog
     */
    private void insertDifferenceField(OperationLog operationLog) {
        List<Map<String,Object>> list = operationLog.getAfterOperatorDatabaseRecordList();
        operationLog.setDifferenceFieldValue(getInsertDifferenceJSONObject(list.get(0)).toString());
    }




    private JSONArray getDeleteDifferenceJSONObject(Map<String, Object> map) {
        JSONArray arr = new JSONArray();
        Iterator<String> itors = map.keySet().iterator();
        while (itors.hasNext()) {
            String key = itors.next();
            JSONObject object = new JSONObject();
            object.put(COLUMN, key);
            object.put(OLD_VALUE, map.get(key));
            object.put(NEW_VALUE, "");
            arr.add(object);
        }
        return arr;
    }

    private JSONArray getInsertDifferenceJSONObject(Map<String, Object> map) {
        JSONArray arr = new JSONArray();
        Iterator<String> itors = map.keySet().iterator();
        while (itors.hasNext()) {
            String key = itors.next();
            JSONObject object = new JSONObject();
            object.put(COLUMN, key);
            object.put(OLD_VALUE, "");
            object.put(NEW_VALUE, map.get(key));
            arr.add(object);
        }
        return arr;
    }

    /**
     * 获取当前登入用户信息
     *
     * @param operationLog
     */
    private void setUserInfo(OperationLog operationLog) {
//        operationLog.setUserId(UserContext.getUser().getUserId());
//        operationLog.setUserName(UserContext.getUser().getUsername());
//        operationLog.setIp(UserContext.getUser().getIp());

    }

    /**
     * 获取方法名，方法参数和参数值
     *
     * @param joinPoint
     * @param operationLog
     */
    private void parseMethodArgsAndValue(ProceedingJoinPoint joinPoint, OperationLog operationLog) {
        String method = joinPoint.getSignature().getName();
        operationLog.setMethod(method);
        // 提取参数和参数值
        Map<String, Object> map = getArgsNameAndArgsVlaue(joinPoint);
        operationLog.setRequestParamsAndValue(map);
    }

    /**
     * 解析注解配置信息
     *
     * @param logAnnocation
     * @param operationLog
     */
    private void parseAnnotationInfo(OptLog logAnnocation, OperationLog operationLog) {
        // 设置操作日志数据
        operationLog.setTableName(logAnnocation.tableName());
        operationLog.setColumnName(logAnnocation.columnName());
        operationLog.setColumnParamterName(logAnnocation.columnParamterName());
        operationLog.setOperationType(logAnnocation.operationType().getValue());
        operationLog.setSummaryColumnName(logAnnocation.summaryColumnName());
    }

    /**
     * 环绕通知最后，查询业务逻辑执行成功后数据库的结果
     *
     * @param logAnnocation
     * @param operationLog
     * @param res
     * @param time
     */
    private void parseRecordAfterExecuteMethod(OptLog logAnnocation,
                                               OperationLog operationLog, Object res, long time) {
        operationLog.setRunTime(time);
        operationLog.setReturnValue(new Gson().toJson(res));
        OperationType operationType = logAnnocation.operationType();
        switch (operationType) {
            case INSERT:
                parseSaveResult(operationLog);
                break;
            case DELETE:
                parseDeleteResult(operationLog);
                break;
            case UPDATE:
                parseUpdateResult(logAnnocation, operationLog);
                break;
            default:
                break;
        }
    }

    /**
     * 删除操作之后数据库结果
     *
     * @param operationLog
     */
    private void parseDeleteResult(OperationLog operationLog) {
        operationLog.setAfterOperatorDatabaseRecordList(
                new ArrayList<>(operationLog.getBeforeOperatorDatabaseRecordList().size()));
    }

    /**
     * 新增操作之后数据库结果
     *
     * @param operationLog
     */
    private void parseSaveResult(OperationLog operationLog) {
        JSONObject jsonObject = JSONObject.parseObject(operationLog.getReturnValue());
        Object id = jsonObject.getJSONObject("body").get(SUCCCESS_ID);
        Map<String,Object> map = apiLogMapper.selectMapByKeyFieldFromTable(
                operationLog.getColumnName(),operationLog.getTableName(),id
        );
        List<Map<String, Object>> list = new ArrayList<>(16);
        list.add(map);
        operationLog.setAfterOperatorDatabaseRecordList(list);
    }

    /**
     * 查询数据库历史记录
     *
     * @param logAnnocation
     * @param operationLog
     */
    private void parseRecordBeforeExecuteMethod(OptLog logAnnocation, OperationLog operationLog) {

        JSONObject argsAndValueJsonObject = convertMapToJsonObject(operationLog.getRequestParamsAndValue());
        String paramsColumnName = operationLog.getColumnParamterName();
        if(StrUtil.isBlank(paramsColumnName)){
            paramsColumnName = StrUtil.toCamelCase(operationLog.getColumnName());
        }
        OperationType operationType = logAnnocation.operationType();

        // insert操作，不需要查询数据库获取历史数据
        if(operationType.equals(OperationType.UPDATE) || operationType.equals(OperationType.DELETE)){
            // 参数值提取
            Object keyFieldValue = getKeyFieldValue(argsAndValueJsonObject, paramsColumnName);
            if (keyFieldValue == null) {
                // 找不到对应属性的值
                log.warn("method:{}\tcolumnParamterName:{} not exist", operationLog.getMethod(),
                        paramsColumnName);
                return;
            }
            operationLog.setColumnValue(keyFieldValue);

        }

        switch (operationType) {

            case INSERT:
            case UNKNOWN:
                doNothing();
                break;
            case UPDATE:
                parseUpdateHistoryRecord(operationLog);
                break;
            case DELETE:
                parseDeleteHistoryRecord(operationLog);
                break;
            default:
                break;

        }
    }

    private void doNothing() {

    }

    /**
     * 查询数据库
     *
     * @param operationLog
     */
    private void parseDeleteHistoryRecord(OperationLog operationLog) {
        Object keyFieldValue = operationLog.getColumnValue();
        List<Object> listKeyFieldValue = partseListFromKeyFieldValue(keyFieldValue);
        if (CollUtil.isEmpty(listKeyFieldValue)) {
            log.warn("method:{},columnParamterName:{},keyFieldValue:{},operatorType:{} 数据提取失败",
                    operationLog.getMethod(), operationLog.getColumnParamterName(), keyFieldValue,
                    operationLog.getOperationType());
            return;
        }
        String columnName = operationLog.getColumnName();
        String tableName = operationLog.getTableName();
        List<Map<String, Object>> historyRecordList = apiLogMapper.selectListMapByKeyFieldFromTable(columnName,
                tableName,
                listKeyFieldValue);
        operationLog.setBeforeOperatorDatabaseRecordList(historyRecordList);
        log.debug("{} {} operation history data from database {}", StrUtil.repeat("+", 12),
                operationLog.getOperationType(),
                StrUtil.repeat("+", 12));
        log.debug("keyField:\t{}\ttableName:{}keyFieldValue{}", columnName, tableName, keyFieldValue);
        log.debug("data base record:\n{}", new Gson().toJson(historyRecordList));
    }

    /**
     * object 解析成List<Object>
     *
     * @param keyFieldValue
     * @return
     */
    private List<Object> partseListFromKeyFieldValue(Object keyFieldValue) {
        List<Object> list = new ArrayList<>();
        if (keyFieldValue instanceof String) {
            String arrStr = (String) keyFieldValue;
            String[] arr = arrStr.split(StrUtil.COMMA);
            for (String item : arr) {
                list.add(item);
            }
        } else if (keyFieldValue.getClass().isArray()) {
            Object[] arr = (Object[]) keyFieldValue;
            for (Object item : arr) {
                list.add(item);
            }
        } else if (keyFieldValue instanceof List) {
            list = (List<Object>) keyFieldValue;
        }

        return list;
    }

    /**
     * 查询更新操作数据
     *
     * @param operationLog
     */
    private void parseUpdateHistoryRecord(OperationLog operationLog) {

        List<Map<String, Object>> historyRecordList = beforeUpateValueOfDataBase(operationLog);
        operationLog.setBeforeOperatorDatabaseRecordList(historyRecordList);
        log.debug("{} {} operation history data from database {}", StrUtil.repeat("+", 12),
                operationLog.getOperationType(),
                StrUtil.repeat("+", 12));
        log.debug("keyField:\t{}\ttableName:{}keyFieldValue{}", operationLog.getColumnName(),
                operationLog.getTableName(), operationLog.getColumnValue());
        log.debug("data base record:\n{}", new Gson().toJson(historyRecordList));
    }


    /**
     * 删除操作之后数据库结果
     *
     * @param logAnnocation
     * @param operationLog
     */
    private void parseUpdateResult(OptLog logAnnocation,
                                   OperationLog operationLog) {

        List<Map<String, Object>> list = beforeUpateValueOfDataBase(operationLog);
        operationLog.setAfterOperatorDatabaseRecordList(list);
        log.debug("{} {} operation history data from database {}", StrUtil.repeat("+", 12),
                logAnnocation.operationType().getValue(),
                StrUtil.repeat("+", 12));
        log.debug("keyField:\t{}\ntableName:{}keyFieldValue{}",
                operationLog.getColumnName(), operationLog.getTableName(), operationLog.getColumnValue());
        log.debug("data base record:\n{}", new Gson().toJson(list));
    }

    private Map<String, Object> queryDataFromDataBase(OperationLog operationLog) {
        String columnName = operationLog.getColumnName();
        String tableName = operationLog.getTableName();
        return apiLogMapper.selectMapByKeyFieldFromTable(columnName, tableName,
                operationLog.getColumnValue());

    }

    /**
     * 更新操作前数据库结果
     *
     * @param operationLog
     * @return
     */
    private List<Map<String, Object>> beforeUpateValueOfDataBase(OperationLog operationLog) {
        Map<String, Object> queryDataFromDataBase = queryDataFromDataBase(operationLog);
        List<Map<String, Object>> list = new ArrayList<>(16);
        list.add(queryDataFromDataBase);
        return list;
    }

    /**
     * 获取方法的参数值
     *
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getArgsNameAndArgsVlaue(ProceedingJoinPoint joinPoint) {
        String[] argsName = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] argsValue = joinPoint.getArgs();
        Map<String, Object> map = new HashMap<>(16);
        for (int i = 0; i < argsName.length; i++) {
            map.put(argsName[i], argsValue[i]);
        }
        return map;
    }

    JSONObject convertMapToJsonObject(Map<String, Object> argsAndValueMap) {
        if (argsAndValueMap == null) {
            return new JSONObject();
        }
        return JSONObject.parseObject(new Gson().toJson(argsAndValueMap));
    }

    /**
     * 根据参数名提取请求参数中对应的值
     *
     * @param argsAndValueJsonObject
     * @param keyField
     * @return
     */
    Object getKeyFieldValue(JSONObject argsAndValueJsonObject, String keyField) {
        if(StrUtil.isBlank(keyField)){
            return null;
        }
        if (argsAndValueJsonObject.containsKey(keyField)) {
            return argsAndValueJsonObject.get(keyField);
        }
        for (String key : argsAndValueJsonObject.keySet()) {
            Object value = argsAndValueJsonObject.get(key);
            if (value instanceof JSONObject) {
                return getKeyFieldValue((JSONObject) value, keyField);
            }
        }

        return null;

    }

    /**
     * 变量值替换
     *
     * @param argNames
     * @param args
     * @param annotation
     * @return
     */
    String getDetail(String[] argNames, Object[] args, OptLog annotation) {
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < argNames.length; i++) {
            map.put(argNames[i], args[i]);
        }
        String detail = annotation.summaryColumnName();
        try {
            detail = "'" + "#{currentUserName}" + "'=>" + detail;
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                detail = detail.replace("{{" + k + "}}", JSON.toJSONString(v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


    @Before("operationLog()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
//        log.info(StrUtil.repeat("=", 24) + "before enter method" + StrUtil.repeat("=", 24));
    }

    @AfterReturning(returning = "ret", pointcut = "operationLog()")
    public void doAfterReturning(Object ret) {
//        log.info(StrUtil.repeat("=", 24) + "method result:" + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("operationLog()")
    public void throwss(JoinPoint jp) {
//        log.info("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("operationLog()")
    public void after(JoinPoint jp) {
//        log.info("方法最后执行.....");
    }
}
