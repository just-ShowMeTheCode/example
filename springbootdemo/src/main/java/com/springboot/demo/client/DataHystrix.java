package com.springboot.demo.client;

import cn.com.citydo.common.restapi.ResponseObject;
import org.springframework.stereotype.Component;

/**
 * @author: megan
 * @mail: meganmail@vip.qq.com
 * @date: 2019/6/19 19:24
 * @version: v1.0
 * @description:
 */
@Component
public class DataHystrix implements DataClient {

    @Override
    public ResponseObject componentCollect(String componentNos, String identify, String name) {
        return null;
    }

    @Override
    public ResponseObject stressTest(String componentNos, String identify, String name, String traceId) {
        return null;
    }


}
