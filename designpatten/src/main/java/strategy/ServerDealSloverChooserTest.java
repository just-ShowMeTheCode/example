package strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/2517:27
 */
@Slf4j
public class ServerDealSloverChooserTest  {
    public static void main(String[] args) {
        ServerDealSloverChooser chooser = new ServerDealSloverChooser();
        ServerDealSlover slover = chooser.chooser("MESSAGE");
        slover.slover(1L,1L);
    }
}
