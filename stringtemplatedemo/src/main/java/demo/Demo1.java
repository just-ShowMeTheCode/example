package demo;

import org.stringtemplate.v4.ST;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author fumj
 * @version 1.0
 * @date 2022/4/7 17:44
 */
public class Demo1 {
    public static void main(String[] args) {
        ST hello = new ST("Hello,<name>");
        hello.add("name","fumj");
        System.out.println(hello.render());

    }
}
