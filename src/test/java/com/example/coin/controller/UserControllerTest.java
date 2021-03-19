package com.example.coin.controller;

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
public class UserControllerTest {
    @Autowired
    UserController userController;

    static UserVO userVO;
    static CodeVO codeVO;
    static LoginVO loginVO;

    @BeforeClass
    public static void init() {
        userVO = new UserVO();
        codeVO = new CodeVO();
        loginVO = new LoginVO();

        userVO.setEmail("test@gmail.com");
        userVO.setPassword("123456");
        userVO.setUsername("registerTest");

        codeVO.setCode("testCode");
        codeVO.setEmail("181250192@smail.nju.edu.cn");

        loginVO.setEmail("test@gmail.com");
        loginVO.setPassword("123456");
    }

    @Test
    public void registerTest1() {
        ResponseVO responseVO = userController.register(userVO);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("success", res);
        Assert.assertEquals("register success", msg);
    }

    @Test
    public void registerTest2() {
        ResponseVO responseVO = userController.register(null);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("failure", res);
        Assert.assertEquals("register failure", msg);
    }

    @Test
    public void codeTest1() {
        ResponseVO responseVO = userController.code(codeVO);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("success", res);
        Assert.assertEquals("success", msg);
    }

    @Test
    public void codeTest2() {
        ResponseVO responseVO = userController.code(null);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("failure", res);
        Assert.assertEquals("failure", msg);
    }

    @Test
    public void loginTest1() {
        ResponseVO responseVO = userController.login(loginVO);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("success", res);
        Assert.assertEquals("registerTest", msg);
    }

    @Test
    public void loginTest2() {
        ResponseVO responseVO = userController.login(null);
        String res = responseVO.getRes();
        String msg = responseVO.getMsg();
        Assert.assertEquals("failure", res);
        Assert.assertEquals("login failure", msg);
    }
}
