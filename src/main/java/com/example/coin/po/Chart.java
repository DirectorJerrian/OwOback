package com.example.coin.po;

public class Chart {
    int id;
    int userId;
    String jsonURL;
    String xmlURL;
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

    public String getXmlURL() {
        return xmlURL;
    }

    public void setXmlURL(String xmlURL) {
        this.xmlURL = xmlURL;
    }

    public Boolean getVariable() {
        return variable;
    }

    public void setVariable(Boolean variable) {
        this.variable = variable;
    }
}
