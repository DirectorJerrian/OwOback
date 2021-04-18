package com.example.coin.service;

import com.example.coin.data.ChartMapper;
import com.example.coin.po.Chart;
import com.example.coin.serviceImpl.ChartServiceImpl;
import com.example.coin.vo.ChartVO;
import com.example.coin.vo.ResponseVO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChartServiceTest {
    private static String SAVE_CHART_SUCCESS="成功保存该知识图谱";
    private static String SAVE_CHART_NULL_FAILURE="知识图谱信息错误，无法保存";
    @InjectMocks
    ChartServiceImpl chartServiceImpl;
    @Mock
    ChartMapper chartMapper;

    static ChartVO chartVO;

    @BeforeClass
    public static void setChart(){
        chartVO=new ChartVO();
    }

    @Test
    public void saveChartTest2(){
        ResponseVO responseVO= chartServiceImpl.saveChart(0,null);
        String res=responseVO.getRes();
        String msg=responseVO.getMsg();
        Assert.assertEquals(res,"failure");
        Assert.assertEquals(msg,SAVE_CHART_NULL_FAILURE);

    }

    @Test
    public void getAllChartsTest1(){
        List<Chart> chartList=new LinkedList<>();
        Chart chart=new Chart();
        chart.setJsonURL("1234");
        chart.setUserId(0);
        chart.setImgURL("1234");
        chartList.add(chart);
        Mockito.when(chartMapper.getAllCharts()).thenReturn(chartList);
        List<ChartVO> chartVOList= chartServiceImpl.getAllCharts();
        Assert.assertEquals(chartVOList.get(0).getJsonURL(),"1234");
        Assert.assertEquals(chartVOList.get(0).getImgURL(),"1234");
        Assert.assertEquals(chartVOList.get(0).getUserId(),0);
    }

    @Test
    public void getAllChartsTest2(){
        Mockito.when(chartMapper.getAllCharts()).thenReturn(null);
        List<ChartVO> chartVOList= chartServiceImpl.getAllCharts();
        Assert.assertEquals(chartVOList,null);
    }
}
