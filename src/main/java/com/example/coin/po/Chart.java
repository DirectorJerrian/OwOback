package com.example.coin.po;

public class Chart {
    int id;
    int userId;
    String jsonName;
    String jsonURL;
    String imgName;
    String imgURL;
    Boolean variable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Boolean getVariable() {
        return variable;
    }

    public void setVariable(Boolean variable) {
        this.variable = variable;
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
