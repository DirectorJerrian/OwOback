package com.example.coin.vo;

import java.util.List;

public class ChartVO {
    List<NodeVO> nodeList;
    List<LinkVO> linkList;
    public List<NodeVO> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<NodeVO> nodeList) {
        this.nodeList = nodeList;
    }

    public List<LinkVO> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<LinkVO> linkList) {
        this.linkList = linkList;
    }


}
