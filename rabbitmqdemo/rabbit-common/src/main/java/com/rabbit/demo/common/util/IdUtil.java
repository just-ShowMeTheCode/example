package com.rabbit.demo.common.util;

import cn.hutool.core.lang.Snowflake;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/4/1614:57
 */
public class IdUtil {
    static long workerId = 1L;
    static long datacenterId = 1L;
    static Snowflake snowflake = cn.hutool.core.util.IdUtil.createSnowflake(workerId, datacenterId);

    public static long getTraceId(){
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        System.out.println(getTraceId());
    }
}
