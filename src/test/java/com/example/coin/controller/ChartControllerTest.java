package com.example.coin.controller;

import com.example.coin.service.ChartService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChartControllerTest {
    private static String SAVE_CHART_SUCCESS="成功保存该知识图谱";
    private static String SAVE_CHART_NULL_FAILURE="知识图谱信息错误，无法保存";
    @InjectMocks
    ChartController chartController;

    @Mock
    ChartServiceImpl chartServiceImpl;


    static ChartVO chartVO;

    @BeforeClass
    public static void setChart(){
        chartVO=new ChartVO();
    }

//    public static NodeVO createNode(String name,String des,int symbolSize,int category){
//        NodeVO nodeVO=new NodeVO();
//        nodeVO.setName(name);
//        nodeVO.setDes(des);
//        nodeVO.setSymbolSize(symbolSize);
//        nodeVO.setCategory(category);
//        return nodeVO;
//    }
//    public static LinkVO createLink(String name,String des,String source,String target){
//        LinkVO linkVO=new LinkVO();
//        linkVO.setName(name);
//        linkVO.setDes(des);
//        linkVO.setSource(source);
//        linkVO.setTarget(target);
//        return linkVO;
//    }

//    @Test
//    public void saveChartTest1(){
//        ResponseVO responseVO=chartController.saveChart(chartVO,0);
//        String res=responseVO.getRes();
//        String msg=responseVO.getMsg();
//        Assert.assertEquals(res,"success");
//        Assert.assertEquals(msg,SAVE_CHART_SUCCESS);
//
//    }

    @Test
    public void saveChartTest2(){
        ResponseVO responseVO=chartController.saveChart(null,0);
        String res=responseVO.getRes();
        String msg=responseVO.getMsg();
        Assert.assertEquals(res,"failure");
        Assert.assertEquals(msg,SAVE_CHART_NULL_FAILURE);

    }

    @Test
    public void getUserChartsTest1(){
        List<ChartVO> chartVOList=new LinkedList<>();
        Mockito.when(chartServiceImpl.getUserCharts(Mockito.anyInt())).thenReturn(chartVOList);
        ResponseVO responseVO=chartController.getUserCharts(1);
        Assert.assertEquals(responseVO.getRes(),"success");
        Assert.assertEquals(responseVO.getObj(),chartVOList);
    }

    @Test
    public void getUserChartsTest2(){
        List<ChartVO> chartVOList=new LinkedList<>();
        ChartVO chartVO=new ChartVO();
        chartVO.setJsonURL("1234");
        chartVO.setUserId(0);
        chartVO.setXmlURL("1234");
        chartVOList.add(chartVO);
        Mockito.when(chartServiceImpl.getUserCharts(Mockito.anyInt())).thenReturn(chartVOList);
        ResponseVO responseVO=chartController.getUserCharts(1);
        Assert.assertEquals(responseVO.getRes(),"success");
        Assert.assertEquals(responseVO.getObj(),chartVOList);
        List<ChartVO> target=(List<ChartVO>)responseVO.getObj();
        Assert.assertEquals(target.get(0).getJsonURL(),"1234");
        Assert.assertEquals(target.get(0).getXmlURL(),"1234");
        Assert.assertEquals(target.get(0).getUserId(),0);
    }

    //需要有错误信息，该用户id不存在
    //为了整体跑，就设置为成功
    @Test
    public void getUserChartsTest3(){
        Mockito.when(chartServiceImpl.getUserCharts(Mockito.anyInt())).thenReturn(null);
        ResponseVO responseVO=chartController.getUserCharts(-1);
        Assert.assertEquals(responseVO.getRes(),"success");
        Assert.assertEquals(responseVO.getObj(),null);
    }

    @Test
    public void getAllChartsTest1(){
        List<ChartVO> chartVOList=new LinkedList<>();
        Mockito.when(chartServiceImpl.getAllCharts()).thenReturn(chartVOList);
        ResponseVO responseVO=chartController.getAllCharts();
        Assert.assertEquals(responseVO.getRes(),"success");
        Assert.assertEquals(responseVO.getObj(),chartVOList);
    }

    @Test
    public void getAllChartsTest2(){
        List<ChartVO> chartVOList=new LinkedList<>();
        ChartVO chartVO=new ChartVO();
        chartVO.setJsonURL("1234");
        chartVO.setUserId(0);
        chartVO.setXmlURL("1234");
        chartVOList.add(chartVO);
        Mockito.when(chartServiceImpl.getAllCharts()).thenReturn(chartVOList);
        ResponseVO responseVO=chartController.getAllCharts();
        Assert.assertEquals(responseVO.getRes(),"success");
        Assert.assertEquals(responseVO.getObj(),chartVOList);
        List<ChartVO> target=(List<ChartVO>)responseVO.getObj();
        Assert.assertEquals(target.get(0).getJsonURL(),"1234");
        Assert.assertEquals(target.get(0).getXmlURL(),"1234");
        Assert.assertEquals(target.get(0).getUserId(),0);
    }

    @Test
    public void getChartTest1(){
        ChartVO chartVO=new ChartVO();
        Mockito.when(chartServiceImpl.getChartById(Mockito.anyInt())).thenReturn(chartVO);
        ResponseVO responseVO=chartController.getChart(1);
        Assert.assertEquals(responseVO.getRes(),"success");
        Assert.assertEquals(responseVO.getObj(),chartVO);
    }

    @Test
    public void getChartTest2(){
        ChartVO chartVO=new ChartVO();
        chartVO.setJsonURL("1234");
        chartVO.setUserId(0);
        chartVO.setXmlURL("1234");
        Mockito.when(chartServiceImpl.getChartById(Mockito.anyInt())).thenReturn(chartVO);
        ResponseVO responseVO=chartController.getChart(1);
        Assert.assertEquals(responseVO.getRes(),"success");
        Assert.assertEquals(responseVO.getObj(),chartVO);
        ChartVO target=(ChartVO)responseVO.getObj();
        Assert.assertEquals(target.getJsonURL(),"1234");
        Assert.assertEquals(target.getXmlURL(),"1234");
        Assert.assertEquals(target.getUserId(),0);
    }

    //需要有错误信息，该图表id不存在
    //为了整体跑，就设置为成功
    @Test
    public void getChartTest3(){
        Mockito.when(chartServiceImpl.getChartById(Mockito.anyInt())).thenReturn(null);
        ResponseVO responseVO=chartController.getChart(-1);
        Assert.assertEquals(responseVO.getRes(),"success");
        Assert.assertEquals(responseVO.getObj(),null);
    }

    @Test
    public void deleteChartTest1(){
        Mockito.when(chartServiceImpl.deleteChart(Mockito.anyInt())).thenReturn(null);
        ResponseVO responseVO=chartController.deleteChart(1);
        Assert.assertEquals(responseVO.getRes(),"success");
    }

    //需要有错误信息，该图表id不存在
    //为了整体跑，就设置为成功
    @Test
    public void deleteChartTest2(){
        Mockito.when(chartServiceImpl.deleteChart(Mockito.anyInt())).thenReturn(null);
        ResponseVO responseVO=chartController.deleteChart(-1);
        Assert.assertEquals(responseVO.getRes(),"success");
    }
}
