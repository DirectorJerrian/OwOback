package com.example.coin.controller;

import com.example.coin.service.ChartService;
import com.example.coin.serviceImpl.ChartServiceImpl;
import com.example.coin.vo.ChartVO;
import com.example.coin.vo.LinkVO;
import com.example.coin.vo.NodeVO;
import com.example.coin.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/chart")
public class ChartController {
    @Autowired
    ChartServiceImpl chartService;


    @RequestMapping(value = "/saveChart",method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO saveChart(@RequestBody ChartVO chartVO){
        return chartService.saveChart(chartVO);
    }
}
