package com.example.coin.service;

import com.example.coin.vo.JsonVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface KgService {
    JsonVO getTriple(String file);
}
