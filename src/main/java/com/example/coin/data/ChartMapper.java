package com.example.coin.data;

import com.example.coin.po.Chart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChartMapper {

    List<Chart> getAllCharts();

    List<Chart> getUserCharts(@Param("userId") int userId);

    int addChart(Chart chart);

    Chart getChartById(@Param("id") int id);

    int deleteChart(@Param("id") int id);
}
