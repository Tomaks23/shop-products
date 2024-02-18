package com.example.shopproducts.model;

import com.example.shopproducts.model.dto.VendorDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private List<VendorDto> vendors;
    private String image_link;
}
