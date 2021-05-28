package com.sample.product.dto;

public class ProductSearch {

    String type;
    String color;
    String address;
    Double minPrice;
    Double maxPrice;
    Integer minGbLimit;
    Integer maxGbLimit;

    ProductSearch(String type, Double minPrice, Double maxPrice, String address, String color, Integer minGbLimit, Integer maxGbLimit) {
        this.type = type;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.address = address;
        this.color = color;
        this.minGbLimit = minGbLimit;
        this.maxGbLimit = maxGbLimit;
    }

    public String getType() {
        return type;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getColor() {
        return color;
    }

    public Integer getMinGbLimit() {
        return minGbLimit;
    }

    public Integer getMaxGbLimit() {
        return maxGbLimit;
    }
}
