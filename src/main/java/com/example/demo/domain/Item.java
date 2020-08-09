package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Integer id;
    private String name;
    private Integer condition;
    private String brand;
    private Double price;
    private Integer shipping;
    private Integer childCategoryId;
    private String childCategoryName;
    private String description;
    private List<Category> ancestorCategories;
}
