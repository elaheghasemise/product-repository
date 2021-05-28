package com.sample.product.enmus;

public enum ProductEnum {
    PHONE("phone"),
    SUBSCRIPTION("subscription");

    ProductEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    private String stringValue;

    public String stringValue() {
        return stringValue;
    }
}
