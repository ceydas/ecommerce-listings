package com.ecommerce.codecase.log.enums;

public enum EnumLogs {
    MS_THRESHOLD(5);

    int value;

    EnumLogs(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
