package com.example.coin.service;

import com.example.coin.vo.ChartVO;
import com.example.coin.vo.LinkVO;
import com.example.coin.vo.NodeVO;
import com.example.coin.vo.ResponseVO;

import java.util.List;

public interface ChartService {
    ResponseVO saveChart(ChartVO chartVO);
}
