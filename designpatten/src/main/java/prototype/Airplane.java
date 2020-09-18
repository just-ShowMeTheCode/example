package prototype;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/9/16 00169:31
 */
public class Airplane implements Cloneable {
    private int x;
    private int y;

    public Airplane(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected Airplane clone() throws CloneNotSupportedException {
        return (Airplane)super.clone();
    }
}
