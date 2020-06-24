package com.springboot.demo.client;

import cn.com.citydo.common.restapi.ResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author: megan
 * @mail: meganmail@vip.qq.com
 * @date: 2019/6/19 19:24
 * @version: v1.0
 * @description:
 */
@FeignClient(value = "credit-data", fallback = DataHystrix.class)
public interface DataClient {

    /**
     * 数据采集获取数据源
     *
     * @param componentNos
     * @param identify
     * @param name
     * @return
     */
    @RequestMapping(value = "/data/componentCollect", method = RequestMethod.POST)
    ResponseObject<Map<String, Object>> componentCollect(@RequestParam("list") String componentNos, @RequestParam("identify") String identify, @RequestParam("name") String name);


    /**
     * 数据采集获取数据源
     *
     * @param componentNos
     * @param identify
     * @param name
     * @return
     */
    @RequestMapping(value = "/data/stressTest", method = RequestMethod.POST)
    ResponseObject stressTest(@RequestParam("list") String componentNos, @RequestParam("identify") String identify,
                              @RequestParam("name") String name, @RequestParam("traceId") String traceId);


}
