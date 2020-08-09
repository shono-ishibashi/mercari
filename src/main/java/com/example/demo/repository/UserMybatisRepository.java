package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserMybatisRepository {
    private final SqlSessionTemplate sqlSessionTemplate;

    private final String STATEMENT = "com.example.demo.mapper.UserMapper";

    public UserDetailsImpl load(String email){
        return sqlSessionTemplate.selectOne(STATEMENT+".load",email);
    }

    public void insert(User user){
        sqlSessionTemplate.selectOne(STATEMENT+".insert",user);
    }
}
