package com.example.shopproducts;

import com.example.shopproducts.model.Meta;
import com.example.shopproducts.model.Product;
import com.example.shopproducts.model.Vendor;
import com.example.shopproducts.model.dto.ProductDto;
import com.example.shopproducts.model.dto.VendorDto;
import com.example.shopproducts.model.response.ProductListResponse;
import com.example.shopproducts.service.ProductService;
import com.example.shopproducts.service.ProductServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductTest {

    @Mock
    private RestTemplate restTemplate;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(restTemplate);
    }

    @Test
    @Order(1)
    void testGetProductData() {
        String expectedData = "Mocked Product Data";

        when(restTemplate.getForObject("https://api.predic8.de/shop/v2/products/", String.class))
                .thenReturn(expectedData);

        String actualData = productService.getProductData();

        assertEquals(expectedData, actualData);
    }

    @Test
    @Order(2)
    void testGetProductsFilter() {
        ProductListResponse expectedResponse = new ProductListResponse();

        ProductDto productDto= new ProductDto();
        productDto.setSelfLink("/shop/v2/products/1");
        productDto.setId(1);
        productDto.setName("Banana");
        Meta meta= new Meta();
        meta.setCount(17);
        meta.setLimit(10);
        meta.setStart(1);
        meta.setNextLink("http://localhost:8080/api?start=2&limit=10&sort=id&order=asc");
        meta.setPreviousLink(null);
        List<ProductDto> products = new ArrayList<>();
        products.add(productDto);
        expectedResponse.setProducts(products);
        expectedResponse.setMeta(meta);
        when(restTemplate.getForObject("https://api.predic8.de/shop/v2/products/?start=1&limit=10&sort=id&order=asc",
                ProductListResponse.class))
                .thenReturn(expectedResponse);

        ProductListResponse actualResponse = productService.getProductsFilter(1, 10, "id", "asc");

        assertEquals(expectedResponse, actualResponse);
        assertNotEquals(425,actualResponse.getMeta().getLimit());
        assertEquals("Banana",actualResponse.getProducts().get(0).getName());
    }

    @Test
    @Order(3)
    void testGetProductById() {
        Product expectedProduct = new Product();
        expectedProduct.setId(1);
        expectedProduct.setName("Banana");
        VendorDto vendorDto= new VendorDto();
        vendorDto.setId(1);
        vendorDto.setName("Exotics Fruit Lair Ltd.");
        vendorDto.setSelfLink("/shop/v2/vendors/1");
        List<VendorDto> vendor= new ArrayList<>();
        vendor.add(vendorDto);
        expectedProduct.setVendors(vendor);
        expectedProduct.setImageLink("/shop/v2/vendors/1");

        when(restTemplate.getForObject("https://api.predic8.de/shop/v2/products/1", Product.class))
                .thenReturn(expectedProduct);


        Product actualProduct = productService.getProductById(1);

        assertEquals(expectedProduct, actualProduct);
        assertNotEquals("Mango", actualProduct.getName());
    }

    @Test
    @Order(4)
    void testGetVendorById() {
        Vendor expectedVendor = new Vendor();
        expectedVendor.setId(1);
        expectedVendor.setName("Exotics Fruit Lair Ltd.");
        expectedVendor.setProductsLink("/shop/v2/vendors/1/products");
        when(restTemplate.getForObject("https://api.predic8.de/shop/v2/vendors/1", Vendor.class))
                .thenReturn(expectedVendor);

        Vendor actualVendor = productService.getVendorById(1);

        assertEquals(expectedVendor, actualVendor);
        assertNotEquals(3,actualVendor.getId());
    }
}
