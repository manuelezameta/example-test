package com.test.testproject.domain;

/**
 *
 * @author Manuel Lezameta
 * 
 * This enum is to get the integer value of each message type
 */
public enum TypeEnum {
    MESSAGE(1), ERROR(2), WARN(3);

    private final int value;

    private TypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
