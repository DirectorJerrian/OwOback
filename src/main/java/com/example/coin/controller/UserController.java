package com.example.coin.controller;

import com.example.coin.po.User;
import com.example.coin.serviceImpl.UserServiceImpl;
import com.example.coin.vo.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    UserServiceImpl userService;


    @PostMapping("/code")
    public String code(@RequestBody CodeVO codeVO) {
        //return "1";
        return userService.sendCode(codeVO);
    }

    @PostMapping("/register")
    public User register() {
        return userService.getUserInfo(1);
    }

    @PostMapping("/login")
    public User login() {
        return userService.getUserInfo(1);
    }
}
