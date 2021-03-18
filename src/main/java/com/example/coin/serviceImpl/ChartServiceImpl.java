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
    private static String SAVE_CHART_NULL_FAILURE="知识图谱信息错误，无法保存";
    public ResponseVO saveChart(ChartVO chartVO){
        if(chartVO==null)return ResponseVO.failure(SAVE_CHART_NULL_FAILURE);
        else if(chartVO.getLinkList()==null || chartVO.getNodeList()==null){
            return ResponseVO.failure(SAVE_CHART_NULL_FAILURE);
        }
//        System.out.print(chartVO.getLinkList().get(0).getName());
//        System.out.print(chartVO.getLinkList().get(1).getName());
//        System.out.print(chartVO.getLinkList().get(2).getName());
//        System.out.print(chartVO.getLinkList().get(3).getName());
        //TODO
        //保存到数据库
        return ResponseVO.success(SAVE_CHART_SUCCESS);
    }
}
