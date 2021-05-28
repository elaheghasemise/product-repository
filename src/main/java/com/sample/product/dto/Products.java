package com.sample.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sample.product.entity.Product;

import java.util.List;


public class Products {

    @JsonProperty("data")
    List<Product> data;

    public Products() {
    }

    public Products(List<Product> data) {
        this.data = data;
    }

    public List<Product> getData() {
        return data;
    }
}
