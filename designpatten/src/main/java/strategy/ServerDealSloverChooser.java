package strategy;

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
public class ServerDealSloverChooser implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Map<String,ServerDealSlover> sloverMap = new HashMap<>();

    @PostConstruct
    private void register(){
        Map<String, ServerDealSlover> beansOfType = applicationContext.getBeansOfType(ServerDealSlover.class);
        sloverMap.putAll(beansOfType);
    }

    public ServerDealSlover chooser(String type){
        return sloverMap.get(type);
    }
}
