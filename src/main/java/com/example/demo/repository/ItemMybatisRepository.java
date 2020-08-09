package com.example.demo.repository;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.myBatisParam.ItemUpdateParam;
import com.example.demo.myBatisParam.PagingParam;

import com.example.demo.myBatisParam.SearchParam;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemMybatisRepository {
    private final SqlSessionTemplate sqlSessionTemplate;

    private final String STATEMENT = "com.example.demo.mapper.ItemMapper";

    public List<Item> pagedFindAll(PagingParam param){
        return sqlSessionTemplate.selectList(STATEMENT+".findAll",param);
    }

    public Item load(Integer id){
        return sqlSessionTemplate.selectOne(STATEMENT+".load",id);
    }

    public List<Item> search(SearchParam param) {
        return sqlSessionTemplate.selectList(STATEMENT+".findBySearch",param);
    }

    public void update(ItemUpdateParam param) {
        sqlSessionTemplate.update(STATEMENT+".itemUpdate",param);
    }

    public void insert(ItemUpdateParam param) {
        sqlSessionTemplate.insert(STATEMENT+".itemInsert",param);
    }
}
