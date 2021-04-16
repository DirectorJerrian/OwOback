package com.example.coin.controller;

import com.example.coin.po.User;
import com.example.coin.serviceImpl.UserServiceImpl;
import com.example.coin.vo.CodeVO;
import com.example.coin.vo.LoginVO;
import com.example.coin.vo.ResponseVO;
import com.example.coin.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    UserServiceImpl userService;


    @PostMapping("/code")
    @ResponseBody
    public ResponseVO code(@RequestBody CodeVO codeVO) {
        if (codeVO!=null){System.out.println(codeVO.getCode());}
        return userService.sendCode(codeVO);
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseVO register(@RequestBody UserVO userVO) {
        return userService.addAccount(userVO);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseVO login(@RequestBody LoginVO loginVO) {
        return userService.verifyPwd(loginVO);
    }

    @GetMapping("/{id}/getUserInfo")
    @ResponseBody
    public ResponseVO getUserInfo(@PathVariable int id) {
        User user = userService.getUserInfo(id);
        if(user==null){
            return ResponseVO.failure("用户信息错误");
        }
        return ResponseVO.success(user);
    }
}
