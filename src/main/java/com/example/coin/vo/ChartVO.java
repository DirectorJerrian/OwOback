package com.example.coin.vo;

public class ChartVO {

    int userId;
    String jsonName;
    String jsonURL;
    String imgName;
    String imgURL;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getJsonURL() {
        return jsonURL;
    }

    public void setJsonURL(String jsonURL) {
        this.jsonURL = jsonURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getJsonName() {
        return jsonName;
    }

    public void setJsonName(String jsonName) {
        this.jsonName = jsonName;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
