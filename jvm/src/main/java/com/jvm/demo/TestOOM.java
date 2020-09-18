package com.jvm.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fumj
 * @projectName example
 * @description: -Xms2m  -Xmx2m  -XX:+HeapDumpOnOutOfMemoryError
 * @date 2020/8/6 000616:54
 */
public class TestOOM {
    public static void main(String[] args){
        oom();
    }

    private static void oom(){
        Map<String, OOMBean> map = new HashMap<String, OOMBean>();
        Object[] array = new Object[1000000];
        for(int i=0; i<1000000; i++){
            String d = new Date().toString();
            OOMBean o = new OOMBean(d, i);
            map.put(i+"_oom", o);
            array[i] = o;
        }
    }


}
class OOMBean{
    String str;
    Integer i;

    public OOMBean(String str, Integer i) {
        this.str = str;
        this.i = i;
    }
}