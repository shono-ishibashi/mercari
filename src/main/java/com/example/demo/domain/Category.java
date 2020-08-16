package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;

    //階層が一つ下のcategoryが入るやつ
    private List<Category> descendantCategories;

    public static void main(String[] args) {
        
    }
}
