package strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/2517:22
 */
@Slf4j
public class EmailSlover extends ServerDealSlover {
    @Override
    public void slover(Long serverId, Long userId) {
      log.info("email slover");
    }

    @Override
    public String[] supports() {
        return new String[]{"EMAIL"};
    }
}
