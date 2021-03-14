package com.example.coin.controller;

import com.example.coin.po.User;
import com.example.coin.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class WebController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World !!!";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/register")
    public String registerPage() {
        return "Register";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public User user() {
        return userService.getUserInfo(1);
    }

}
