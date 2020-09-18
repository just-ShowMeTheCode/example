package prototype;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/9/16 00169:30
 */
public class Prototype1 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Airplane instance = AirplaneSingleton.getInstance();
        for (int i = 0; i < 200; i++) {
            Airplane airplane = instance.clone();
        }
    }

}
