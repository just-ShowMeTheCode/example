package com.cloudemo.serve.client;

import com.cloudemo.common.bean.Person;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/916:16
 */
@Slf4j
@Component
public class PartApiServiceFactory implements FallbackFactory<PartApiService> {

    @Override
    public PartApiService create(Throwable throwable) {
//        throwable.printStackTrace();
        log.error("part service error\t{}", throwable.getMessage());
        return new PartApiService() {
            @Override
            public ResponseEntity<String> queryApiData(Person person) {
                return ResponseEntity.badRequest().body("credit-part error");
            }
        };
    }
}
