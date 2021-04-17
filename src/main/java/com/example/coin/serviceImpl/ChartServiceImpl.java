package com.example.coin.serviceImpl;

import com.example.coin.Converter.ChartConverter;
import com.example.coin.data.ChartMapper;
import com.example.coin.po.Chart;
import com.example.coin.service.ChartService;
import com.example.coin.vo.ChartVO;
import com.example.coin.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {
    @Autowired
    ChartMapper chartMapper;

    @Override
    public ResponseVO saveChart(int id, MultipartFile file) {
        if (file == null) {
            return ResponseVO.failure("source is null");
        }
        ChartVO chartVO=new ChartVO();
        Chart chart = ChartConverter.INSTANCE.v2p(chartVO);
        try {
            chartMapper.addChart(chart);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.failure("图谱保存失败");
        }
        return ResponseVO.success(chartVO);
    }

    @Override
    public List<ChartVO> getAllCharts() {
        try {
            return ChartConverter.INSTANCE.p2v(chartMapper.getAllCharts());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ChartVO> getUserCharts(int userId) {
        return null;
    }

    @Override
    public ChartVO getChartById(int id) {
        return null;
    }

    @Override
    public ResponseVO deleteChart(int id) {
        return null;
    }
}
