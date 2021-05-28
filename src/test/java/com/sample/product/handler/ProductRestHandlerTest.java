package com.sample.product.handler;

import com.sample.product.SearchProductClient;
import com.sample.product.dto.ProductSearch;
import com.sample.product.dto.ProductSearchBuilder;
import com.sample.product.dto.Products;
import com.sample.product.enmus.ProductEnum;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/*
 First run ProductRepositoryApplication
 Then run tests
*/
public class ProductRestHandlerTest {

    SearchProductClient client = new SearchProductClient();

    @Test
    public void getProductsByAddress() {
        ProductSearch productSearch = new ProductSearchBuilder().withAddress("stockholm").build();
        Optional<Products> products = client.searchProduct(productSearch);
        Assert.assertTrue(products.isPresent());
        Assert.assertEquals(40, products.get().getData().size());
    }

    @Test
    public void getProductsByAddressAndType() {
        ProductSearch productSearch = new ProductSearchBuilder().withAddress("stockholm")
                .withType(ProductEnum.PHONE.stringValue()).build();
        Optional<Products> products = client.searchProduct(productSearch);
        Assert.assertTrue(products.isPresent());
        Assert.assertEquals(15, products.get().getData().size());
    }

    @Test
    public void getProductsByWrongPropertyType() {
        ProductSearch productSearch = new ProductSearchBuilder().withType(ProductEnum.PHONE.stringValue())
                .withMinGbLimit(10).withMaxGbLimit(50).build();
        Optional<Products> products = client.searchProduct(productSearch);
        Assert.assertTrue(products.isPresent());
        Assert.assertEquals(0, products.get().getData().size());
    }

    @Test
    public void getProductsByPropertyTypeAndLimits() {
        ProductSearch productSearch = new ProductSearchBuilder().withType(ProductEnum.SUBSCRIPTION.stringValue())
                .withMinGbLimit(10).withMaxGbLimit(50).build();
        Optional<Products> products = client.searchProduct(productSearch);
        Assert.assertTrue(products.isPresent());
        Assert.assertEquals(58, products.get().getData().size());
    }

    @Test
    public void getProductsWithAllCriteria() {
        ProductSearch productSearch = new ProductSearchBuilder().withAddress("stockholm")
                .withType(ProductEnum.PHONE.stringValue()).withColor("gul").withMinPrice(10.00).withMaxPrice(100.00)
                .build();
        Optional<Products> products = client.searchProduct(productSearch);
        Assert.assertTrue(products.isPresent());
        Assert.assertEquals(1, products.get().getData().size());
    }
}

