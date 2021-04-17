package com.example.coin.service;

import com.example.coin.vo.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    static UserVO userVO1;
    static UserVO userVO2;
    static String code;
    static LoginVO loginVO1;
    static LoginVO loginVO2;

    @BeforeClass
    public static void init() {
        userVO1 = new UserVO();
        userVO2 = new UserVO();
        loginVO1 = new LoginVO();
        loginVO2 = new LoginVO();

        userVO1.setEmail("test2@gmail.com");
        userVO1.setPassword("123456");
        userVO1.setUsername("registerTest");

        userVO2.setEmail("123@qq.com");
        userVO2.setPassword("123456");
        userVO2.setUsername("registerTest");

        code="181250192@smail.nju.edu.cn";

        loginVO1.setEmail("123@qq.com");
        loginVO1.setPassword("123123");

        loginVO2.setEmail("123@qq.com");
        loginVO2.setPassword("123456");
    }

    @Test
    public void registerTest1() {
        ResponseVO responseVO = userService.addAccount(userVO1);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("success", res);
        Assert.assertEquals("register success", msg);
    }

    @Test
    public void registerTest2() {
        ResponseVO responseVO = userService.addAccount(userVO2);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("failure", res);
        Assert.assertEquals("该邮箱已被使用！", msg);
    }

    @Test
    public void codeTest1() {
        ResponseVO responseVO = userService.sendCode(code);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("success", res);
        Assert.assertEquals("success", msg);
    }

    @Test
    public void codeTest2() {
        ResponseVO responseVO = userService.sendCode(null);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("failure", res);
        Assert.assertEquals("source is null", msg);
    }

    @Test
    public void loginTest1() {
        ResponseVO responseVO = userService.verifyPwd(loginVO1);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("success", res);
        Assert.assertEquals("Test", msg);
    }

    @Test
    public void loginTest2() {
        ResponseVO responseVO = userService.verifyPwd(loginVO2);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("failure", res);
        Assert.assertEquals("login failure", msg);
    }
}
