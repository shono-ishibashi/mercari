package com.example.demo.myBatisParam;

import lombok.Data;

@Data
public class ItemUpdateParam {
    private Integer id;
    private String name;
    private Integer category;
    private String brand;
    private Double price;
    private Integer condition;
    private String description;
}
