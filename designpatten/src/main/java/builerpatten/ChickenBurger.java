package builerpatten;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/6 000613:57
 */
public class ChickenBurger extends Burger {
    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "chickenBurger";
    }
}
