package com.ecommerce.codecase.log.enums;

public enum EnumHttpRequest {
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    GET("GET");

    String name;


    EnumHttpRequest(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
