package com.cloudemo.serve.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/716:01
 */
@FeignClient(value = "credit-part",fallback = PartApiServiceFallBack.class)
public interface PartApiService {

    @RequestMapping(value = "/api/queryApiData",method = RequestMethod.POST)
    ResponseEntity<String> queryApiData(@RequestParam(value = "name") String name);
}
