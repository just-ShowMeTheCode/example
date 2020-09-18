package strategy.demo2;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/9/16 001613:53
 */
public class Demo2 {
    public static void main(String[] args) {
        Strategy strategy = new Addition();
        System.out.println(strategy.calculate(1,2).intValue());
    }
}
