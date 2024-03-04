package com.example.shopproducts.model;

import com.example.shopproducts.model.dto.VendorDto;
import com.fasterxml.jackson.annotation.JsonAlias;
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
    @JsonAlias("image_link")
    private String imageLink;
}
