package com.proxy.staticproxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2019/12/1110:46
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Lion implements Cat {
    private String name;
    private int runSpeed;

    public String getName() {
        return name;
    }

    public Lion setName(String name) {
        this.name = name;
        return this;
    }

    public int getRunSpeed() {
        return runSpeed;
    }

    public Lion setRunSpeed(int runSpeed) {
        this.runSpeed = runSpeed;
        return this;
    }

    @Override
    public String eatFood() {
        StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append(" Lion eat food ");
        log.info(builder.toString());
        return builder.toString();
    }

    @Override
    public boolean running() {
        StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append(" Lion is running,speed is ")
                .append(runSpeed);
        log.info(builder.toString());
        return false;
    }
}
