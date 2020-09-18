package state;

/**
 * @author fumj
 * @projectName example
 * @description: 当前状态
 * @date 2020/9/16 001614:09
 */
public interface State {

    /**
     * 停车挡
     */
    void P(Car car);

    /**
     * 倒车挡
     */
    void R(Car car);

    /**
     * 空挡
     */
    void N(Car car);

    /**
     * 前进挡
     */
    void D(Car car);
}
