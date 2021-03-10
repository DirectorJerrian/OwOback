package com.example.coin.service;

import com.example.coin.data.UserMapper;
import com.example.coin.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {
    User getUserInfo(int id);

}
