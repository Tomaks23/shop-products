package com.example.shopproducts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private Integer count;
    private Integer start;
    private Integer limit;
    private String next_link;
    private String previous_link;
}
