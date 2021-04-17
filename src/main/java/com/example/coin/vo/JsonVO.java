package com.example.coin.vo;

import com.google.gson.JsonObject;

public class JsonVO {
    private JsonObject jsonObject;

    public JsonObject getJsonObject(){
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject){
        this.jsonObject=jsonObject;
    }
}
