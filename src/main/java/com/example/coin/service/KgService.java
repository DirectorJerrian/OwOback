package com.example.coin.service;

import com.example.coin.vo.DataVO;
import com.example.coin.vo.ResponseVO;

public interface KgService {
    ResponseVO getTriple(DataVO dataVO);

    ResponseVO getExample(DataVO dataVO);
}
