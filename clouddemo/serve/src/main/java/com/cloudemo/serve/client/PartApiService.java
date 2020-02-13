package com.cloudemo.serve.client;

import com.cloudemo.common.bean.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/716:01
 */
//@FeignClient(value = "credit-part",fallback = PartApiServiceFallBack.class)
@FeignClient(value = "credit-part",fallbackFactory = PartApiServiceFactory.class)
public interface PartApiService {

    @PostMapping(value = "/api/queryApiData")
    ResponseEntity<String> queryApiData(@RequestBody Person person);
}
