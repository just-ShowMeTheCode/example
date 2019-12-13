import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1310:33
 */
@Slf4j
public class CglibTest {

    @Test
    public void test1(){
        SimpleClass simpleClass = (SimpleClass)Enhancer.create(SimpleClass.class, new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "hello world";
            }
        });

       log.info("{}",simpleClass.test("cglib"));
        log.info("{}",simpleClass.toString());
        log.info("{}",simpleClass.getClass());
        log.info("{}",simpleClass.hashCode());


    }


}
