package com.rabbitmq.demo.server.controller;

import cn.hutool.http.HttpUtil;

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
        String resposne = HttpUtil.get(SERVER_URL + PORT + "/produceor/sendMessage");

    }

    @org.junit.Test
    public void sendMessage2() {
        String resposne = HttpUtil.get(SERVER_URL + PORT + "/produceor/sendMessage2");

    }



}
