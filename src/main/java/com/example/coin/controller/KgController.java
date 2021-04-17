package com.example.coin.controller;

import com.example.coin.serviceImpl.KgServiceImpl;
import com.example.coin.vo.JsonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/kg")
public class KgController {
    @Autowired
    KgServiceImpl kgService;

    @GetMapping(value = "/{data}/getKg")
    @ResponseBody
    public JsonVO getKg(@PathVariable String data){
        return kgService.getTriple(data);
    }

}
