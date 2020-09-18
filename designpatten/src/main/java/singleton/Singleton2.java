package singleton;

/**
 * @author fumj
 * @projectName example
 * @description: 懒汉模式
 * @date 2020/9/16 00169:27
 */
public class Singleton2 {

    private static Singleton2 singleton2 = null;

    public static Singleton2 getInstance(){
        if (singleton2 == null){
            synchronized (Singleton2.class){
                singleton2 = new Singleton2();
            }
        }
        return singleton2;
    }
}
