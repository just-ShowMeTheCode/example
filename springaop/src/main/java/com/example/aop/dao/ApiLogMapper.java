package com.example.aop.dao;

import com.example.aop.model.ApiLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aop.model.OperationLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fumj
 * @since 2020-02-13
 */
public interface ApiLogMapper extends BaseMapper<ApiLog> {

    /**
     * 根据列查询表内容
     * @param columnName
     * @param tableName
     * @param columnValue
     * @return
     */
    Map<String,Object> selectMapByKeyFieldFromTable(@Param("columnName") String columnName,
                                                    @Param("tableName") String tableName,
                                                    @Param("columnValue") Object columnValue);

    /**
     * 根据列批量查询表内容
     * @param columnName
     * @param tableName
     * @param listKeyFieldValue
     * @return
     */
    List<Map<String, Object>> selectListMapByKeyFieldFromTable(@Param("columnName")String columnName,
                                                               @Param("tableName") String tableName,
                                                               @Param("list") List<Object> listKeyFieldValue);

    /**
     * 操作日志批量插入
     * @param logList
     */
    void batchInsert(List<OperationLog> logList);
}
