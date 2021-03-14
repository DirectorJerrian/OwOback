package com.example.coin.serviceImpl;

import com.example.coin.service.ChartService;
import com.example.coin.vo.ChartVO;
import com.example.coin.vo.LinkVO;
import com.example.coin.vo.NodeVO;
import com.example.coin.vo.ResponseVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {
    private static String SAVE_CHART_SUCCESS="成功保存该知识图谱";
    public ResponseVO saveChart(ChartVO chartVO){
        System.out.print(chartVO.getLinkList().get(0).getName());
        System.out.print(chartVO.getLinkList().get(1).getName());
        System.out.print(chartVO.getLinkList().get(2).getName());
        System.out.print(chartVO.getLinkList().get(3).getName());
        return ResponseVO.success(SAVE_CHART_SUCCESS);
    }
}
