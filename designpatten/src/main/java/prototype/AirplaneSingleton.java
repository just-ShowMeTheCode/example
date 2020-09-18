package prototype;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/9/16 00169:39
 */
public class AirplaneSingleton {
    private static Airplane airplane = new Airplane(1,2);

    public static Airplane getInstance(){
        return airplane;
    }
}
