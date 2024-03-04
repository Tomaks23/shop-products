package com.example.shopproducts.controller;


import com.example.shopproducts.model.Product;
import com.example.shopproducts.model.response.ProductListResponse;
import com.example.shopproducts.model.Vendor;
import com.example.shopproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class ProductController {


    public final ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProducts(){
        return productService.getProductData();
    }

    @GetMapping
    public ResponseEntity<ProductListResponse> getProductsFilter(
            @RequestParam(defaultValue = "1") Integer start,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order) {

        return ResponseEntity.ok(productService.getProductsFilter(start, limit, sort, order));
    }



    @GetMapping("/product/{productId}")
    public Product getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/vendor/{vendorId}")
    public Vendor getVendorById(@PathVariable Integer vendorId) {
        return productService.getVendorById(vendorId);
    }
}
