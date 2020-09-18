package strategy.demo2;

import java.math.BigDecimal;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/9/16 001613:52
 */
public class Subtraction implements Strategy {
    @Override
    public BigDecimal calculate(int x, int y) {
        return new BigDecimal(x).subtract(new BigDecimal(y));
    }
}
