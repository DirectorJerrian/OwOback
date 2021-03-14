package com.example.coin.vo;

import java.util.HashMap;
import java.util.Map;

public class ResponseVO {
    String res;
    String msg;
    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public static ResponseVO success(String msg){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setRes("success");
        responseVO.setMsg(msg);
        return responseVO;
    }
    public static ResponseVO failure(String msg){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setRes("failure");
        responseVO.setMsg(msg);
        return responseVO;
    }

}
