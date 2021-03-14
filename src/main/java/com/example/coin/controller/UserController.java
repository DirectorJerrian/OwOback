package com.example.coin.controller;

import com.example.coin.po.User;
import com.example.coin.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World !!!";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        return userService.getUserInfo(1);
    }


}
