package com.cloudemo.part.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/816:36
 */
@FeignClient(value = "credit-data",fallback = DataSourceClientFallBack.class)
public interface DataSourceClient {

    @PostMapping(value = "/api/queryApiData")
    ResponseEntity<String> queryApiData(@RequestParam("name") String name);
}
