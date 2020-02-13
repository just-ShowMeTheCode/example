package controller;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/1/817:17
 */
@Slf4j
public class ServeApiControllerTest {

    @Test
    public void test1() throws Exception{
        Map<String,Object> params = new HashMap<>();
        params.put("name","fumj");
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 5000; i++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String response = HttpUtil.post("http://localhost:7500/api/queryApiData",params);
                            if(response.contains("error")){
                                log.error(Thread.currentThread().getName() + "\t" + response);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            TimeUnit.MILLISECONDS.sleep(500);
        }


        log.info("finish request");
    }
}
