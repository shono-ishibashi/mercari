package com.example.demo.controller;

import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand/search")
public class BrandRestController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/")
    public List<String> search(String brand) {
        return itemService.findBrandName(brand);
    }

}
