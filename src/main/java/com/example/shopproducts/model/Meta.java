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
public class Meta {
    private Integer count;
    private Integer start;
    private Integer limit;
    @JsonAlias("next_link")
    private String nextLink;
    @JsonAlias("previous_link")
    private String previousLink;
}
