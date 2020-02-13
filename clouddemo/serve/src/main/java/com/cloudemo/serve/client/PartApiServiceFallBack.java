package com.cloudemo.serve.client;

import com.cloudemo.common.bean.Person;
import com.netflix.hystrix.HystrixRequestLog;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/811:44
 */
@Component
@Slf4j
public class PartApiServiceFallBack implements PartApiService {

    @Override
    public ResponseEntity<String> queryApiData(Person person) {
        return ResponseEntity.badRequest().body("credit-part error");
    }

}
