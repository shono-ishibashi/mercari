package com.example.demo.controller;

import com.example.demo.domain.Category;
import com.example.demo.myBatisParam.CategoryParam;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategorySelectRestController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/getParentCategory")
    @ResponseBody
    public List<Category> getParentCategories(@RequestBody Map<String,Integer> grandParentCategoryId){

        Integer id = grandParentCategoryId.get("grandParentCategoryId");

        List<Category> categoryList = categoryService.parentCategories(id);
        return categoryList;
    }

    @RequestMapping("/getChildCategory")
    @ResponseBody
    public Category getChildCategory(@RequestBody Map<String,Integer> forChildJson){

        CategoryParam param = new CategoryParam();

        param.setParentId(forChildJson.get("parentCategoryId"));
        param.setGrandParentId(forChildJson.get("grandParentCategoryId"));


        return categoryService.searchCategory(param);
    }



}

