package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Search {
    private String itemName;
    private Integer grandParentCategory;
    private Integer parentCategory;
    private Integer childCategory;
    private String brand;
    private Integer page;
}
