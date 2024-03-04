package com.example.shopproducts.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {
    private Integer id;
    private String name;
    @JsonAlias("products_link")
    private String productsLink;
}
