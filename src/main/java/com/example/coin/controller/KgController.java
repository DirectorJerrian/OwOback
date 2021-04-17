package com.example.coin.controller;

import com.example.coin.serviceImpl.KgServiceImpl;
import com.example.coin.vo.JsonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/chart")
public class KgController {
    @Autowired
    KgServiceImpl kgService;

    @PostMapping(value = "/getKg")
    @ResponseBody
    public String getKg(@RequestBody String data){
        return kgService.getTriple(data);
    }

}
