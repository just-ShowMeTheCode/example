package singleton;

/**
 * @author fumj
 * @projectName example
 * @description: 饿汉模式
 * @date 2020/9/16 00169:26
 */
public class Singleton1 {
    private static Singleton1 singleton1 = new Singleton1();

    public static Singleton1 getInstance(){
        return singleton1;
    }

}
