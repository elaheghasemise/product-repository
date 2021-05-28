package com.sample.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.product.dto.ProductSearch;
import com.sample.product.dto.Products;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Component
public class SearchProductClient {

    static final Logger logger = Logger.getLogger(SearchProductClient.class);

    public Optional<Products> searchProduct(ProductSearch productSearch) {
        Products products = null;
        try {
            URI uri = searchProductUri(productSearch);
            var response = sendGetRequest(uri);
            if (response == null) {
                return Optional.empty();
            }
            products = new ObjectMapper().readValue(response.getEntity().getContent(), Products.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }
        return Optional.ofNullable(products);
    }

    private CloseableHttpResponse sendGetRequest(URI uri) {
        CloseableHttpResponse response = null;
        try {
            HttpGet httpget = new HttpGet(uri);
            httpget.setHeader("Content-Type", "application/json");
            response = HttpClients.createDefault().execute(httpget);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return response;
    }

    private URI searchProductUri(ProductSearch productSearch) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http").setHost("localhost:8080").setPath("/search/product")
                .setParameter("city", productSearch.getAddress())
                .setParameter("type", productSearch.getType())
                .setParameter("color", productSearch.getColor())
                .setParameter("minPrice", productSearch.getMinPrice() != null ? productSearch.getMinPrice().toString() : null)
                .setParameter("maxPrice", productSearch.getMaxPrice() != null ? productSearch.getMaxPrice().toString() : null)
                .setParameter("minGbLimit", productSearch.getMinGbLimit() != null ? productSearch.getMinGbLimit().toString() : null)
                .setParameter("maxGbLimit", productSearch.getMaxGbLimit() != null ? productSearch.getMaxGbLimit().toString() : null);
        return uriBuilder.build();
    }

}
