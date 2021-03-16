package com.example.coin.service;

import com.example.coin.data.UserMapper;
import com.example.coin.po.User;
import com.example.coin.vo.CodeVO;
import com.example.coin.vo.ResponseVO;
import com.example.coin.vo.UserVO;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


public interface UserService {
    User getUserInfo(int id);
    ResponseVO sendCode(CodeVO codeVO);
    ResponseVO addAccount(UserVO userVO);
    ResponseVO verifyPwd(CodeVO codeVO);
}
