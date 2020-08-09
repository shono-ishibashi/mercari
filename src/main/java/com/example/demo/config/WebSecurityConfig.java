package com.example.demo.config;

import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService loginService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img_noodle/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .authorizeRequests()
                //指定したパターンごとに制限をかける
                .antMatchers(
                          "/login_form"
                        , "/login"
                        , "/register_form"
                        , "/register"
                ).permitAll()//制限なし

                .anyRequest().authenticated()
                //アクセスの許可
                .and()

                .formLogin()

                .defaultSuccessUrl("/",true)
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/login_form")


                .and()

                .logout()
                //  ログアウト時の遷移先URL
                .logoutSuccessUrl("/noodle/show-list");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(loginService)
                .passwordEncoder(passwordEncoder());
    }

    @Autowired
    void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}