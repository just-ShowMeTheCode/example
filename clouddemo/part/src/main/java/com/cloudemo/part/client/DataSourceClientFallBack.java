package com.cloudemo.part.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/816:37
 */
@Component
public class DataSourceClientFallBack implements DataSourceClient{
    @Override
    public ResponseEntity<String> queryApiData(String name) {
        return ResponseEntity.badRequest().body("data server error");
    }
}
