package com.example.shopproducts.service;

import com.example.shopproducts.model.Product;
import com.example.shopproducts.model.Vendor;
import com.example.shopproducts.model.response.ProductListResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductListResponse getProductsFilter(Integer start, Integer limit, String sort, String order);
    Product getProductById(Integer productId);
    Vendor getVendorById(Integer vendorId);
    String getProductData();
}
