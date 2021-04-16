package com.example.coin.controller;

import com.example.coin.serviceImpl.KgServiceImpl;
import com.example.coin.vo.JsonVO;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/kg")
public class KgController {
    @Autowired
    KgServiceImpl kgService;

    @RequestMapping(value = "/getKg",method = RequestMethod.POST)
    @ResponseBody
    public JsonVO getKg(@RequestBody String txt){
        return kgService.getTriple(txt);
    }

}
