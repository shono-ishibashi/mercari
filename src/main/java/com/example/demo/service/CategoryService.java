package com.example.demo.service;

import com.example.demo.domain.Category;
import com.example.demo.myBatisParam.CategoryParam;
import com.example.demo.repository.CategoryMybatisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryMybatisRepository categoryMybatisRepository;

    public Category searchCategory(CategoryParam categoryParam) {
        return categoryMybatisRepository.searchCategory(categoryParam);
    }

    public List<Category> grandParentCategories(){
        return categoryMybatisRepository.grandParentCategories();
    }

    public List<Category> parentCategories(Integer grandParentId){
        return categoryMybatisRepository.parentCategories(grandParentId);
    }
}
