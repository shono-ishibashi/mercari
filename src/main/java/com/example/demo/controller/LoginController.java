package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.form.UserForm;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ModelAttribute
    private UserForm searchFormSetUp(){
        return new UserForm();
    }

    @RequestMapping("/login_form")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/register_form")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/register")
    public String register(Model model, UserForm userForm){

        //入力したメールアドレスが存在していたら、INSERT
        if(loginService.exitsEmail(userForm.getEmail())){
            model.addAttribute("exitsEmailError",true);
            return "forward:/register_form";
        }else {
            User user = new User();
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());

            loginService.insert(user);
            return "forward:/login_form";
        }
    }
}
