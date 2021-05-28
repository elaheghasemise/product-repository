package com.sample.product.service;

import com.sample.product.dto.ProductSearch;
import com.sample.product.dto.ProductSearchBuilder;
import com.sample.product.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    public void findByAddressWithoutCriteria() {
        List<Product> products = productService.find(new ProductSearchBuilder().build());
        Assert.assertEquals(100, products.size());
    }

    @Test
    public void findByAddressIgnoreCase() {
        ProductSearch productSearch = new ProductSearchBuilder().withAddress("stockholm").build();
        List<Product> products = productService.find(productSearch);
        Assert.assertEquals(40, products.size());
    }

    @Test
    public void findByMaxPrice() {
        ProductSearch productSearch = new ProductSearchBuilder().withMaxPrice(130.00).build();
        List<Product> products = productService.find(productSearch);

        Assert.assertEquals(17, products.size());
    }

    @Test
    public void findByAddressAndBetweenPrices() {
        ProductSearch productSearch = new ProductSearchBuilder().withMinPrice(100.0).withMaxPrice(1000.0)
                .withAddress("stockholm").build();
        List<Product> products = productService.find(productSearch);
        Assert.assertEquals(31, products.size());
    }

}
