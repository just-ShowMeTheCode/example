package com.example.aop.aop;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.example.aop.annotation.OptLog;
import com.example.aop.model.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Pointcut("@annotation(com.example.aop.annotation.OptLog)")
    public void operationLog(){}

    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable  {
        Object res = null;
        long time = System.currentTimeMillis();
        try {
            res = joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            return res;
        } finally {
            try {
                //方法执行完成后增加日志
                addOperationLog(joinPoint,res,time);
            }catch (Exception e){
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }

        }

    }

    private void addOperationLog(JoinPoint joinPoint, Object res, long time){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        OperationLog operationLog = new OperationLog();
        operationLog.setRunTime(time);
        operationLog.setReturnValue(JSON.toJSONString(res));
        operationLog.setId(UUID.fastUUID().toString());
        operationLog.setArgs(JSON.toJSONString(joinPoint.getArgs()));
        operationLog.setCreateTime(new Date());
        operationLog.setMethod(signature.getDeclaringTypeName() + signature.getName());
        operationLog.setUserId("#{currentUserId}");
        operationLog.setUserName("#{currentUserName}");
        OptLog logAnnocation = signature.getMethod().getAnnotation(OptLog.class);
        if(logAnnocation != null){
            operationLog.setLevel(logAnnocation.level());
            operationLog.setDescribe(getDetail(((MethodSignature) joinPoint.getSignature()).getParameterNames(),
                    joinPoint.getArgs(),logAnnocation));
            operationLog.setOperationType(logAnnocation.operationType().getValue());
            operationLog.setOperationUnit(logAnnocation.operationUnit().getValue());
        }
        log.info("日志记录：" + operationLog.toString());

    }

    String getDetail(String[] argNames,Object[] args,OptLog annotation){
        Map<Object,Object> map = new HashMap<>();
        for (int i = 0; i < argNames.length; i++) {
            map.put(argNames[i],args[i]);
        }
        String detail = annotation.detail();
        try {
            detail = "'" + "#{currentUserName}" + "'=>" + detail;
            for (Map.Entry<Object, Object> entry : map.entrySet()){
                Object k = entry.getKey();
                Object v = entry.getValue();
                detail = detail.replace("{{" + k + "}}",JSON.toJSONString(v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    @Before("operationLog()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        log.info(StrUtil.repeat("=",24) + "before enter method" + StrUtil.repeat("=",24));
    }

    @AfterReturning(returning = "ret",pointcut = "operationLog()")
    public void doAfterReturning(Object ret){
        log.info(StrUtil.repeat("=",24) + "method result:" + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("operationLog()")
    public void throwss(JoinPoint jp){
        log.info("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("operationLog()")
    public void after(JoinPoint jp){
        log.info("方法最后执行.....");
    }
}
