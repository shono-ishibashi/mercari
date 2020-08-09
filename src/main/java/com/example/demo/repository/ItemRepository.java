package com.example.demo.repository;

import com.example.demo.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository  {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private final static RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
        Item item = new Item();

        item.setId(rs.getInt("id"));
        item.setName(rs.getString("name"));
        item.setCondition(rs.getInt("condition"));
        item.setPrice(rs.getDouble("price"));
        item.setShipping(rs.getInt("shipping"));
        item.setDescription(rs.getString("description"));

        return item;
    };


    /**
     *
     * item を DBから全件検索してくる
     *
     * @return 検索結果
     */
    public List<Item> findAll(){
        String sql = "SELECT id, name, condition, category, brand, price, shipping, description FROM items ORDER BY name";
        return template.query(sql,ITEM_ROW_MAPPER);
    }





    public List<String> findBrandName(String brandPartOfName){
        String sql = "SELECT brand FROM items WHERE name like :brandPartOfName";
        SqlParameterSource param = new MapSqlParameterSource().addValue("brandPartOfName","%"+brandPartOfName+"%");
        return template.queryForList(sql,param,String.class);
    }

}

