package com.proxy.dymanic.jdk;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1115:04
 */
@Slf4j
public class SoftwareEngineer implements Person {

    @Override
    public void say() {
        log.info(this.getClass().getName() + "say hello");
    }
}
