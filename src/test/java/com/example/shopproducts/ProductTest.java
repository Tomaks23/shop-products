package com.example.shopproducts;

import com.example.shopproducts.model.Product;
import com.example.shopproducts.model.Vendor;
import com.example.shopproducts.model.dto.VendorDto;
import com.example.shopproducts.model.response.ProductListResponse;
import com.example.shopproducts.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

public class ProductTest {

    @Mock
    private RestTemplate restTemplate;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(restTemplate);
    }

    @Test
    void testGetProductData() {
        // Mock the response from the REST API
        String expectedData = "Mocked Product Data";
        when(restTemplate.getForObject("https://api.predic8.de/shop/v2/products/", String.class))
                .thenReturn(expectedData);

        // Call the method under test
        String actualData = productService.getProductData();

        // Verify the result
        assertEquals(expectedData, actualData);
    }

    @Test
    void testGetProductsFilter() {
        // Mock the response from the REST API
        ProductListResponse expectedResponse = new ProductListResponse();
        // Set up expected response data...
        when(restTemplate.getForObject("https://api.predic8.de/shop/v2/products/?start=1&limit=10&sort=id&order=asc",
                ProductListResponse.class))
                .thenReturn(expectedResponse);

        // Call the method under test
        ProductListResponse actualResponse = productService.getProductsFilter(1, 10, "id", "asc");

        // Verify the result
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetProductById() {
        Product expectedProduct = new Product();
        expectedProduct.setId(1);
        expectedProduct.setName("Banana");
        VendorDto vendorDto= new VendorDto();
        vendorDto.setId(1);
        vendorDto.setName("Exotics Fruit Lair Ltd.");
        vendorDto.setSelf_link("/shop/v2/vendors/1");
        List<VendorDto> vendor= new ArrayList<>();
        vendor.add(vendorDto);
        expectedProduct.setVendors(vendor);
        expectedProduct.setImage_link("/shop/v2/vendors/1");

        when(restTemplate.getForObject("https://api.predic8.de/shop/v2/products/1", Product.class))
                .thenReturn(expectedProduct);


        Product actualProduct = productService.getProductById(1);

        assertEquals(expectedProduct, actualProduct);
        assertNotEquals("Mango", actualProduct.getName());
    }

    @Test
    void testGetVendorById() {
        // Mock the response from the REST API
        Vendor expectedVendor = new Vendor();
        // Set up expected vendor data...
        when(restTemplate.getForObject("https://api.predic8.de/shop/v2/vendors/456", Vendor.class))
                .thenReturn(expectedVendor);

        // Call the method under test
        Vendor actualVendor = productService.getVendorById(456);

        // Verify the result
        assertEquals(expectedVendor, actualVendor);
    }
}
