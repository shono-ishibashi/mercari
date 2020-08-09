package com.example.demo.myBatisParam;

import lombok.Data;

@Data
public class CategoryParam {

    //depth 1 の id
    private Integer grandParentId;

    //depth 2 の id
    private Integer parentId;
}
