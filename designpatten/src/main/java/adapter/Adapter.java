package adapter;

/**
 * @author fumj
 * @projectName example
 * @description: 适配器模式
 * @date 2020/9/16 001611:15
 */
public class Adapter implements Usb {

    private Hidi hidi;

    public Adapter(Hidi hidi) {
        this.hidi = hidi;
    }

    @Override
    public void connectComputer() {
        // 执行转换逻辑
    }
}
