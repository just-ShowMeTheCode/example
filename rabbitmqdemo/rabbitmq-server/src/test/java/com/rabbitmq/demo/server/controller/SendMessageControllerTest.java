package com.rabbitmq.demo.server.controller;

import cn.hutool.http.HttpUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/4/1615:15
 */
public class SendMessageControllerTest {

    private final static String SERVER_URL = "http://localhost:";
    private final static int PORT = 8020;


    @org.junit.Test
    public void sendMessage() {
        int messageCount = 1;
        String resposne = HttpUtil.get(SERVER_URL + PORT + "/produceor/sendMessage?messageCount="+messageCount);

    }

    @org.junit.Test
    public void send() {
        int messageCount = 10000;
        int sleep = 0;
        String resposne = HttpUtil.get(SERVER_URL + PORT
                + "/produceor/send?messageCount="+messageCount + "&sleep=" + sleep);

    }


    @org.junit.Test
    public void prioritySend() {
        int messageCount = 50;
        String resposne =
                HttpUtil.get(SERVER_URL + PORT + "/produceor/prioritySendMessage?messageCount="+messageCount);

    }

    @org.junit.Test
    public void sendMessage2() {
        String resposne = HttpUtil.get(SERVER_URL + PORT + "/produceor/sendMessage2");
        ApplicationContext context = new ClassPathXmlApplicationContext("");
    }



}
