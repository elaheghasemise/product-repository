package com.sample.product.dto;

public class ProductSearchBuilder {
    private String type;
    private String color;
    private String address;
    private Double minPrice;
    private Double maxPrice;
    private Integer minGbLimit;
    private Integer maxGbLimit;

    public ProductSearchBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ProductSearchBuilder withColor(String color) {
        this.color = color;
        return this;
    }

    public ProductSearchBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public ProductSearchBuilder withMinPrice(Double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public ProductSearchBuilder withMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public ProductSearchBuilder withMinGbLimit(Integer minGbLimit) {
        this.minGbLimit = minGbLimit;
        return this;
    }

    public ProductSearchBuilder withMaxGbLimit(Integer maxGbLimit) {
        this.maxGbLimit = maxGbLimit;
        return this;
    }

    public ProductSearch build() {
        return new ProductSearch(type, minPrice, maxPrice, address, color, minGbLimit, maxGbLimit);
    }

}
