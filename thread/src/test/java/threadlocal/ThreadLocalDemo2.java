package threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fumj
 * @projectName example
 * @description: 不同的线程中调用同一个类对象的ThreadLocal变量的get()方法，输出依据线程的不同而不同。
 * @date 2019/12/2510:21
 */
@Slf4j
public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        ResourceHolder.putResource("conn",new Conn("connection1"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("conn: {}",ResourceHolder.getResource("conn"));
            }
        }).start();

        log.info("conn: {}",ResourceHolder.getResource("conn"));
    }
}

class ResourceHolder{
    public static ThreadLocal<Map<Object,Object>> mapThreadLocal = new ThreadLocal<Map<Object, Object>>();

    public static void putResource(Object key,Object value){
        if(mapThreadLocal.get() == null){
            mapThreadLocal.set(new HashMap<Object, Object>());
        }
        mapThreadLocal.get().put(key, value);
    }

    public static Object getResource(Object key){
        if(mapThreadLocal.get() == null){
            mapThreadLocal.set(new HashMap<Object, Object>());
        }
        return mapThreadLocal.get().get(key);
    }

    public static void clearResource(){
        if(mapThreadLocal.get() != null){
            mapThreadLocal.remove();
        }
    }
}

class Conn{
    private String name;

    public Conn(String name) {
        this.name = name;
    }

    public Conn() {
    }

    public String getName() {
        return name;
    }

    public Conn setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Conn [name=" + name + "]";
    }
}
