package com.example.coin.controller;

import com.example.coin.vo.ChartVO;
import com.example.coin.vo.ResponseVO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChartControllerTest {
    private static String SAVE_CHART_SUCCESS="成功保存该知识图谱";
    private static String SAVE_CHART_NULL_FAILURE="知识图谱信息错误，无法保存";
    @Autowired
    ChartController chartController;

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
}
