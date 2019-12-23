package com.example.transaction.dao;

import com.example.transaction.model.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fumj
 * @since 2019-12-17
 */
public interface PersonMapper extends BaseMapper<Person> {

    void truncateTable();

}
