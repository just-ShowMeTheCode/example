package state;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/9/16 001614:18
 */
public class Car {

    State state = new N();

    public Car setState(State state) {
        this.state = state;
        return this;
    }

    public void P() {
        state.P(this);
    }


    public void R() {
        state.R(this);
    }


    public void N() {
        state.N(this);
    }


    public void D() {
        state.D(this);
    }
}
