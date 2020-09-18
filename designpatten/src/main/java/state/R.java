package state;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/9/16 001614:15
 */
public class R implements State {
    @Override
    public void P(Car car) {
        car.setState(new P());
    }

    @Override
    public void R(Car car) {

    }

    @Override
    public void N(Car car) {
        car.setState(new N());
    }

    @Override
    public void D(Car car) {

    }
}
