package com.cloudemo.serve.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/811:44
 */
@Component
public class PartApiServiceFallBack implements PartApiService {
    @Override
    public ResponseEntity<String> queryApiData(String name) {
        return ResponseEntity.badRequest().body("credit-part error");
    }
}
