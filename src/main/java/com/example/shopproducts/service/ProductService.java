package com.example.shopproducts.service;

import com.example.shopproducts.model.Product;
import com.example.shopproducts.model.response.ProductListResponse;
import com.example.shopproducts.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    private final RestTemplate restTemplate;
    private static String PRODUCTS_DATA_URL = "https://api.predic8.de/shop/v2/products/";

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProductData() {
        String apiUrl = PRODUCTS_DATA_URL;
        return restTemplate.getForObject(apiUrl, String.class);
    }

    public ProductListResponse getProductsFilter(Integer start, Integer limit,String sort, String order) {
        String apiUrl = PRODUCTS_DATA_URL + "?start=" + start + "&limit=" + limit + "&sort=" + sort + "&order=" + order;
        return restTemplate.getForObject(apiUrl, ProductListResponse.class);
    }

    public Product getProductById(Integer productId) {
        String apiUrl = "https://api.predic8.de/shop/v2/products/" + productId;

        return restTemplate.getForObject(apiUrl, Product.class);
    }

    public Vendor getVendorById(Integer vendorId) {
        String apiUrl = "https://api.predic8.de/shop/v2/vendors/" + vendorId;
        return restTemplate.getForObject(apiUrl, Vendor.class);
    }
}
