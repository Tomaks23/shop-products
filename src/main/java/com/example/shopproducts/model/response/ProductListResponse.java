package com.example.shopproducts.model.response;

import com.example.shopproducts.model.Meta;
import com.example.shopproducts.model.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {
    private Meta meta;
    private List<ProductDto> products;
}
