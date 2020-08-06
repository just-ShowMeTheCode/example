package builerpatten;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/6 000613:56
 */
public class VegBurger extends Burger {
    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "vgeBurger";
    }
}
