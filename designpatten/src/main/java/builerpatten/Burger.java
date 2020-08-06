package builerpatten;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/6 000613:53
 */
public abstract class Burger implements Item {

    @Override
    public abstract float price() ;

    @Override
    public Packing packing() {
        return new Wrapper();
    }
}
