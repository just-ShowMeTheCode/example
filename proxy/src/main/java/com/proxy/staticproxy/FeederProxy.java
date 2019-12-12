package com.proxy.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1110:55
 */
@Slf4j
public class FeederProxy implements Cat {

    private Cat cat;

    public FeederProxy() {
    }

    public FeederProxy(Cat cat) {
        if(cat instanceof Cat){
            this.cat = cat;
        }

    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {

        if(cat instanceof Cat){
            this.cat = cat;
        }

    }

    @Override
    public String eatFood() {
        log.info("proxy exec eat food");
        return cat.eatFood();
    }

    @Override
    public boolean running() {
        log.info("proxy exec run");
        return cat.running();
    }
}
