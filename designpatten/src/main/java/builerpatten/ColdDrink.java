package builerpatten;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/6 000613:55
 */
public abstract class ColdDrink implements Item{

    @Override
    public Packing packing() {
        return new Bottle();
    }
}
