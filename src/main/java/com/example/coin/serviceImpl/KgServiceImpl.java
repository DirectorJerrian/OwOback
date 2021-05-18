package com.example.coin.serviceImpl;
import java.io.*;
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
    //以下函数用来测试
    public ResponseVO getExample(DataVO dataVO) {
        String jsonStr = "";
        try {
            File jsonFile = new File("src\\main\\resources\\kg/target.json");
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return ResponseVO.success(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    //以下函数实际调用
    public ResponseVO getTriple(DataVO dataVO) {
        String file=dataVO.getDataString();
        file=file.replace("\n","");
        file=file.replace(" ","");
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
//            proc = Runtime.getRuntime().exec("src\\main\\resources\\kg\\dist/main.exe "+file);
            proc = Runtime.getRuntime().exec("./kg/main "+file);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader test = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            System.out.println(test.read());
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
                    int ran_Category=r.nextInt(6);
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
