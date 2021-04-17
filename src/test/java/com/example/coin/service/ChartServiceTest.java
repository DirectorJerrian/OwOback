package com.example.coin.service;

import com.example.coin.data.ChartMapper;
import com.example.coin.po.Chart;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChartServiceTest {
    private static String SAVE_CHART_SUCCESS="成功保存该知识图谱";
    private static String SAVE_CHART_NULL_FAILURE="知识图谱信息错误，无法保存";
    @InjectMocks
    ChartService chartService;
    @Mock
    ChartMapper chartMapper;

    static ChartVO chartVO;

    @BeforeClass
    public static void setChart(){
        chartVO=new ChartVO();
//        List<NodeVO> nodeVOList=new LinkedList<>();
//        List<LinkVO> linkVOList=new LinkedList<>();
//        nodeVOList.add(createNode("01","123",50,0));
//        nodeVOList.add(createNode("02","123",50,0));
//        linkVOList.add(createLink("12","123","01","02"));
    }

//    @Test
//    public void saveChartTest1(){
//        ResponseVO responseVO=chartService.saveChart(0,chartVO);
//        String res=responseVO.getRes();
//        String msg=responseVO.getMsg();
//        Assert.assertEquals(res,"success");
//        Assert.assertEquals(msg,SAVE_CHART_SUCCESS);
//
//    }

    @Test
    public void saveChartTest2(){
        ResponseVO responseVO=chartService.saveChart(0,null);
        String res=responseVO.getRes();
        String msg=responseVO.getMsg();
        Assert.assertEquals(res,"failure");
        Assert.assertEquals(msg,SAVE_CHART_NULL_FAILURE);

    }

    @Test
    public void saveChartTest1(){
        Chart chart=new Chart();
        Mockito.when(chartMapper.addChart(Mockito.any(Chart.class))).thenReturn(null);
//        ResponseVO responseVO=chartService.saveChart();
    }
}
