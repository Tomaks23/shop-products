package com.example.shopproducts.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorDto {
    private Integer id;
    private String name;
    @JsonAlias("self_link")
    private String selfLink;
}
