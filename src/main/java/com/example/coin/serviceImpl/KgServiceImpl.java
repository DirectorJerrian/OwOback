package com.example.coin.serviceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.example.coin.service.KgService;
import com.example.coin.vo.JsonVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class KgServiceImpl implements KgService {
    public JsonVO getTriple(String file) {
        JsonVO jsonVO=new JsonVO();
        Process proc;
        boolean change=false;
        int numOfNodes=0;
        int numOfLinks=0;
        JsonObject jsonContainer=new JsonObject();
        jsonContainer.addProperty("title","知识图谱");
        JsonArray nodes=new JsonArray();
        JsonArray links=new JsonArray();
        try {
            proc = Runtime.getRuntime().exec("python36 ./kg/main.py "+file);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                if (line.equals("links")){
                    change=true;
                    continue;
                }
                if (!change){
                    numOfNodes++;
                    JsonObject node=new JsonObject();
                    node.addProperty("name",line);
                    node.addProperty("des","nodedes"+numOfNodes);
                    node.addProperty("symbol","circle");
                    node.addProperty("symbolSize","70");
                    node.addProperty("type","highlight");
                    JsonObject color=new JsonObject();
                    color.addProperty("color","#5470c6");
                    node.add("itemStyle",color);
                    JsonObject frontSize=new JsonObject();
                    frontSize.addProperty("frontSize",12);
                    node.add("label",frontSize);
                    node.addProperty("category",0);

                    nodes.add(node);
                }
                else{
                    numOfLinks++;
                    JsonObject link=new JsonObject();
                    String[] object=line.split(" ");
                    link.addProperty("source",object[0]);
                    link.addProperty("target",object[1]);
                    link.addProperty("name",object[2]);
                    link.addProperty("des","link"+numOfLinks+"des");

                    links.add(link);
                }
            }
            jsonContainer.add("nodes",nodes);
            jsonContainer.add("links",links);
            jsonContainer.addProperty("isChartFixed",false);
            JsonArray potions=new JsonArray();
            jsonContainer.add("potions",potions);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonVO.setJsonObject(jsonContainer);
        return jsonVO;
    }
}
