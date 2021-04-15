//package com.example.coin;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import com.example.coin.service.KgService;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import org.springframework.stereotype.Service;
//
//class Main {
//    public static void main(String[] args) {
//        String file = "环境很好，位置独立性很强，比较安静很切合店名，半闲居，偷得半日闲。点了比较经典的菜品，味道果然不错！烤乳鸽，超级赞赞赞，脆皮焦香，肉质细嫩，超好吃。艇仔粥料很足，香葱自己添加，很贴心。金钱肚味道不错，不过没有在广州吃的烂，牙口不好的慎点。凤爪很火候很好，推荐。最惊艳的是长寿菜，菜料十足，很新鲜，清淡又不乏味道，而且没有添加调料的味道，搭配的非常不错！";
//        Process proc;
//        boolean change=false;
//        int numOfNodes=0;
//        int numOfLinks=0;
//        JsonObject jsonContainer=new JsonObject();
//        jsonContainer.addProperty("title","知识图谱");
//        JsonArray nodes=new JsonArray();
//        JsonArray links=new JsonArray();
//        try {
//            proc = Runtime.getRuntime().exec("python36 ./kg/main.py "+file);
//            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                if (line.equals("links")){
//                    change=true;
//                    continue;
//                }
//                if (!change){
//                    numOfNodes++;
//                    JsonObject node=new JsonObject();
//                    node.addProperty("name",line);
//                    node.addProperty("des","nodedes"+numOfNodes);
//                    node.addProperty("symbol","circle");
//                    node.addProperty("symbolSize","70");
//                    node.addProperty("type","highlight");
//                    JsonObject color=new JsonObject();
//                    color.addProperty("color","#5470c6");
//                    node.add("itemStyle",color);
//                    JsonObject frontSize=new JsonObject();
//                    frontSize.addProperty("frontSize",12);
//                    node.add("label",frontSize);
//                    node.addProperty("category",0);
//
//                    nodes.add(node);
//                }
//                else{
//                    numOfLinks++;
//                    JsonObject link=new JsonObject();
//                    String[] object=line.split(" ");
//                    link.addProperty("source",object[0]);
//                    link.addProperty("target",object[1]);
//                    link.addProperty("name",object[2]);
//                    link.addProperty("des","link"+numOfLinks+"des");
//
//                    links.add(link);
//                }
//            }
//            jsonContainer.add("nodes",nodes);
//            jsonContainer.add("links",links);
//            jsonContainer.addProperty("isChartFixed",false);
//            JsonArray potions=new JsonArray();
//            jsonContainer.add("potions",potions);
//            System.out.println(jsonContainer);
//            in.close();
//            int res=proc.waitFor();
//            System.out.print(res);
//
//            BufferedReader ine = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
//            String linee = null;
//            while((linee = ine.readLine()) != null){
//                System.out.println(linee);
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
