package builerpatten;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/6 000613:58
 */
public class CokeDrink extends ColdDrink {
    @Override
    public String name() {
        return "cokeDrint";
    }

    @Override
    public float price() {
        return 15.0f;
    }
}
