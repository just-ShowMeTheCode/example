package builerpatten;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/6 000613:59
 */
public class PepsiDrink extends ColdDrink {
    @Override
    public String name() {
        return "pepsiDrink";
    }

    @Override
    public float price() {
        return 14.0f;
    }
}
