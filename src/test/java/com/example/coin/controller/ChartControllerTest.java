package com.example.coin.controller;

import com.example.coin.po.Node;
import com.example.coin.vo.ChartVO;
import com.example.coin.vo.LinkVO;
import com.example.coin.vo.NodeVO;
import com.example.coin.vo.ResponseVO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    @Autowired
    ChartController chartController;

     static ChartVO chartVO;

    @BeforeClass
    public static void setChart(){
        chartVO=new ChartVO();
        List<NodeVO> nodeVOList=new LinkedList<>();
        List<LinkVO> linkVOList=new LinkedList<>();
        nodeVOList.add(createNode("01","123",50,0));
        nodeVOList.add(createNode("02","123",50,0));
        linkVOList.add(createLink("12","123","01","02"));
        chartVO.setLinkList(linkVOList);
        chartVO.setNodeList(nodeVOList);
    }

    public static NodeVO createNode(String name,String des,int symbolSize,int category){
        NodeVO nodeVO=new NodeVO();
        nodeVO.setName(name);
        nodeVO.setDes(des);
        nodeVO.setSymbolSize(symbolSize);
        nodeVO.setCategory(category);
        return nodeVO;
    }
    public static LinkVO createLink(String name,String des,String source,String target){
        LinkVO linkVO=new LinkVO();
        linkVO.setName(name);
        linkVO.setDes(des);
        linkVO.setSource(source);
        linkVO.setTarget(target);
        return linkVO;
    }

    @Test
    public void saveChartTest1(){
        ResponseVO responseVO=chartController.saveChart(chartVO);
        String res=responseVO.getRes();
        String msg=responseVO.getMsg();
        Assert.assertEquals(res,"success");
        Assert.assertEquals(msg,SAVE_CHART_SUCCESS);

    }

    @Test
    public void saveChartTest2(){
        ResponseVO responseVO=chartController.saveChart(null);
        String res=responseVO.getRes();
        String msg=responseVO.getMsg();
        Assert.assertEquals(res,"failure");
        Assert.assertEquals(msg,SAVE_CHART_NULL_FAILURE);

    }
}
