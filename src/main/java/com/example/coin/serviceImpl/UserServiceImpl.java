package com.example.coin.serviceImpl;

import com.example.coin.data.UserMapper;
import com.example.coin.po.User;
import com.example.coin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserInfo(int id){
        return userMapper.getUserInfo(id);
    }


}
