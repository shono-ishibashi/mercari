package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserDetailsImpl;
import com.example.demo.repository.UserMybatisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    private UserMybatisRepository userMybatisRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMybatisRepository.load(username);
    }

    public boolean exitsEmail(String email) {

        boolean exitsEmail;
        UserDetailsImpl getUser = userMybatisRepository.load(email);

        if(isNull(getUser)){
            exitsEmail = false;
        }else{
            exitsEmail = true;
        }

        return exitsEmail;
    }

    public void insert(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMybatisRepository.insert(user);
    }
}
