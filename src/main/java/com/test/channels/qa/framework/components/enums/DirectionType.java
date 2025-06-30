package com.test.channels.qa.framework.components.enums;

/**
 * @author AbhishekJain
 *
 */
public enum DirectionType {
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right");

    private String direction;

    DirectionType(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
