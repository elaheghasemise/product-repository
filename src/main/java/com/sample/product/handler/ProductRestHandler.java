package com.sample.product.handler;

import com.sample.product.dto.ProductSearchBuilder;
import com.sample.product.dto.Products;
import com.sample.product.entity.Product;
import com.sample.product.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class ProductRestHandler {
    static final Logger logger = Logger.getLogger(ProductRestHandler.class);

    @Value("jsondata.txt")
    private ClassPathResource resource;

    @Autowired
    private ProductService productService;

    @PostConstruct
    private void postConstruct() {
        try {
            productService.initializeMongoDB(resource.getInputStream());
            logger.info("Mongo db data initialized successfully");
        } catch (IOException e) {
            logger.error("Failed to initialized Mongo db data");
        }
    }

    @GetMapping(value = "/search/product", consumes = APPLICATION_JSON_VALUE)
    public Products searchProducts(@RequestParam(name = "type") String type,
                                   @RequestParam(name = "minPrice") Double minPrice,
                                   @RequestParam(name = "maxPrice") Double maxPrice,
                                   @RequestParam(name = "city") String city,
                                   @RequestParam(name = "color") String color,
                                   @RequestParam(name = "minGbLimit") Integer minGBLimit,
                                   @RequestParam(name = "maxGbLimit") Integer maxGbLimit) {

        var productSearch = new ProductSearchBuilder().withType(type).withAddress(city).
                withColor(color).withMinPrice(minPrice).withMaxPrice(maxPrice).withMinGbLimit(minGBLimit)
                .withMaxGbLimit(maxGbLimit).build();
        List<Product> list = productService.find(productSearch);
        return new Products(list);
    }
}
