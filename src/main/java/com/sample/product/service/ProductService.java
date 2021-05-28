package com.sample.product.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.product.dto.ProductSearch;
import com.sample.product.enmus.ProductEnum;
import com.sample.product.entity.Product;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    MongoTemplate mongoTemplate;

    private static final String PRICE = "price";
    private static final String GB_LIMIT = "gbLimit";

    public void initializeMongoDB(InputStream inputStream) throws IOException {
        List<Product> productList = readProductsFromStream(inputStream);
        setColor(productList);
        setGbLimit(productList);
        productList.forEach(this::save);
    }

    public List<Product> find(ProductSearch productSearch) {
        var query = queryBuilder(productSearch);
        return mongoTemplate.find(query, Product.class, "product");
    }

    public Product save(Product product) {
        mongoTemplate.save(product);
        return product;
    }

    private Query queryBuilder(ProductSearch productSearch) {
        var query = new Query();
        if (productSearch == null) {
            return query;
        }
        if (!StringUtils.isEmpty(productSearch.getType())) {
            query.addCriteria(Criteria.where("type").regex(productSearch.getType(), "i"));
        }
        if (!StringUtils.isEmpty(productSearch.getAddress())) {
            query.addCriteria(Criteria.where("storeAddress").regex(productSearch.getAddress(), "i"));
        }
        if (!StringUtils.isEmpty(productSearch.getColor())) {
            query.addCriteria(Criteria.where("color").regex(productSearch.getColor(), "i"));
        }
        if (productSearch.getMinPrice() != null && productSearch.getMaxPrice() != null) {
            query.addCriteria(Criteria.where(PRICE).gte(productSearch.getMinPrice()).lte(productSearch.getMaxPrice()));

        } else if (productSearch.getMinPrice() != null) {
            query.addCriteria(Criteria.where(PRICE).gte(productSearch.getMinPrice()));

        } else if (productSearch.getMaxPrice() != null) {
            query.addCriteria(Criteria.where(PRICE).lte(productSearch.getMaxPrice()));
        }

        if (productSearch.getMinGbLimit() != null && productSearch.getMaxGbLimit() != null) {
            if (productSearch.getMaxGbLimit().equals(productSearch.getMinGbLimit())) {
                query.addCriteria(Criteria.where(GB_LIMIT).is(productSearch.getMaxGbLimit()));
            } else {
                query.addCriteria(Criteria.where(GB_LIMIT).
                        gte(productSearch.getMinGbLimit()).
                        lte(productSearch.getMaxGbLimit()));
            }
        } else if (productSearch.getMinGbLimit() != null) {
            query.addCriteria(Criteria.where(GB_LIMIT).gte(productSearch.getMinGbLimit()));

        } else if (productSearch.getMaxGbLimit() != null) {
            query.addCriteria(Criteria.where(GB_LIMIT).lte(productSearch.getMaxGbLimit()));
        }
        return query;
    }

    private List<Product> readProductsFromStream(InputStream inputStream) throws IOException {
        var products = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        List<Product> productList = Arrays.asList(new ObjectMapper().readValue(products, Product[].class));
        inputStream.close();
        return productList;
    }

    private void setColor(List<Product> productList) {
        productList.stream().filter(p -> p.getType().equals(ProductEnum.PHONE.stringValue())).
                forEach(p -> p.setColor(p.getProperties().split(":")[1]));
    }

    private void setGbLimit(List<Product> productList) {
        productList.stream().filter(p -> p.getType().equals(ProductEnum.SUBSCRIPTION.stringValue())).
                forEach(p -> p.setGbLimit(Integer.valueOf(p.getProperties().split(":")[1])));
    }
}
