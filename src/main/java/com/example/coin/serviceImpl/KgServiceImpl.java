package com.example.coin.serviceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import com.example.coin.service.KgService;
import com.example.coin.vo.DataVO;
import com.example.coin.vo.ResponseVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class KgServiceImpl implements KgService {
    public ResponseVO getTriple(DataVO dataVO) {
        String file=dataVO.getDataString();
        System.out.println(file);
        Gson gson=new Gson();
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
            Random r=new Random(1);
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
                    int ran_symbol=r.nextInt(100);
                    if (ran_symbol<25)
                        node.addProperty("symbol","circle");
                    else if (ran_symbol<50)
                        node.addProperty("symbol","triangle");
                    else if (ran_symbol<75)
                        node.addProperty("symbol","rectangle");
                    else
                        node.addProperty("symbol","diamond");
                    node.addProperty("symbolSize",30);
                    node.addProperty("type","highlight");
                    JsonObject color=new JsonObject();
                    node.add("itemStyle",color);
                    JsonObject frontSize=new JsonObject();
                    frontSize.addProperty("frontSize",12);
                    node.add("label",frontSize);
                    int ran_Category=r.nextInt(10);
                    node.addProperty("category",ran_Category);

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
        String result=gson.toJson(jsonContainer);
        return ResponseVO.success(result);
    }
}
