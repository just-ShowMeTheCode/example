import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1314:00
 */
@Slf4j
public class SimpleClass {
    public SimpleClass() {
    }

    public String test(String str){
        if(StrUtil.isBlank(str)){
            str = "hello";
        }
        return "say" + str;
    }
}
