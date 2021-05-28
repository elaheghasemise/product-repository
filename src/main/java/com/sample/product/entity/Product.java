package com.sample.product.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
    private String type;
    private String properties;
    private Double price;
    private String storeAddress;
    private String color;
    private Integer gbLimit;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getGbLimit() {
        return gbLimit;
    }

    public void setGbLimit(Integer gbLimit) {
        this.gbLimit = gbLimit;
    }
}
