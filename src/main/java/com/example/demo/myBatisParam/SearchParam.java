package com.example.demo.myBatisParam;

import lombok.Data;

@Data
public class SearchParam {
    private Integer child;
    private String brand;
    private String itemName;

    private Integer limit;
    private Integer offset;
}
