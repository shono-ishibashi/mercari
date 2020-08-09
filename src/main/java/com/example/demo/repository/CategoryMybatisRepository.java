package com.example.demo.repository;

import com.example.demo.domain.Category;
import com.example.demo.myBatisParam.CategoryParam;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryMybatisRepository {

    private final SqlSessionTemplate sqlSessionTemplate;

    private final String STATEMENT = "com.example.demo.mapper.CategoryMapper";

    public Category searchCategory(CategoryParam categoryParam){
        return sqlSessionTemplate.selectOne(STATEMENT + ".searchCategory",categoryParam);
    }

    public List<Category> grandParentCategories (){
        return sqlSessionTemplate.selectList(STATEMENT + ".grandParentList");
    }

    public List<Category> parentCategories (Integer grandParentId){
        return sqlSessionTemplate.selectList(STATEMENT + ".parentCategoryList",grandParentId);
    }
}

