import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/2515:09
 */
@Slf4j
public class StringRepeatPerfenceTest {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        String str = String.join("", Collections.nCopies(1000000,"="));
        log.info("Collections.nCopies cost time {}",System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        StrUtil.repeat('=',1000000);
        log.info("StrUtil.repeat cost time {}",System.currentTimeMillis() - start);

    }
}
