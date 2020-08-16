package com.example.demo.service;

import com.example.demo.domain.Item;


import com.example.demo.domain.Search;
import com.example.demo.myBatisParam.ItemUpdateParam;
import com.example.demo.myBatisParam.PagingParam;
import com.example.demo.myBatisParam.SearchParam;
import com.example.demo.repository.ItemMybatisRepository;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMybatisRepository itemMybatisRepository;

    private final Integer PAGE_SIZE = 30;

    public List<Item> pagedFindAll(Integer page){

        Integer offset = PAGE_SIZE * page ;

        PagingParam param = new PagingParam(PAGE_SIZE,offset);

        return itemMybatisRepository.pagedFindAll(param);

    }

    public Item load(Integer id){
        return itemMybatisRepository.load(id);
    }

    public List<String> findBrandName(String partOfName){
        return itemRepository.findBrandName(partOfName);
    }

    public List<Item> search(Search search) {

        Integer page = search.getPage();
        Integer offset = PAGE_SIZE * page ;

        SearchParam param = new SearchParam();

        param.setChild(search.getChildCategory());
        param.setBrand(search.getBrand());
        param.setItemName(search.getItemName());

        param.setLimit(PAGE_SIZE);
        param.setOffset(offset);



        return itemMybatisRepository.search(param);
    }

    public void update(ItemUpdateParam param) {


        if(isNull(param.getId())){
            itemMybatisRepository.insert(param);
        }else {
            itemMybatisRepository.update(param);
        }
    }
}
