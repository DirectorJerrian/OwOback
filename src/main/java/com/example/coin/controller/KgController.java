package com.example.coin.controller;

import com.example.coin.serviceImpl.KgServiceImpl;
import com.example.coin.vo.DataVO;
import com.example.coin.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/chart")
public class KgController {
    @Autowired
    KgServiceImpl kgService;

    @PostMapping(value = "/getKg")
    @ResponseBody
    public ResponseVO getKg(@RequestBody DataVO dataVO){
        return kgService.getTriple(dataVO);
        //return kgService.getExample(dataVO);
    }

}
