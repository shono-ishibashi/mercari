package com.example.demo.form;

import lombok.Data;

@Data
public class SearchForm {
    private String itemName;
    private Integer grandParentCategory;
    private Integer parentCategory;
    private Integer childCategory;
    private String brand;
    private Integer page;
    private String url;
}
