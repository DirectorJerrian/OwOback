package com.example.coin.serviceImpl;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.example.coin.service.KgService;
import com.example.coin.vo.DataVO;
import com.example.coin.vo.ResponseVO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

@Service
public class KgServiceImpl implements KgService {
    public static ArrayList<ArrayList<String>> dic=new ArrayList<>();
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
    //以下函数实际调用: 提取三元组
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

    public ArrayList<ArrayList<String>> getDic(){
        return dic;
    }
    public Boolean fuse(String a,String b) throws IOException {
        //如果dic为空，加载字典
        if(dic.size()==0) {
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream("src\\main\\resources\\kg\\data\\similarDic.txt")));
            String strTmp = "";
            while ((strTmp = buffReader.readLine()) != null) {
                String [] strTmpList=strTmp.split(" ");
                ArrayList<String> temp=new ArrayList<>();
                Collections.addAll(temp,strTmpList);
                dic.add(temp);
            }
        }
        for (int i=0;i<dic.size();i++){
            ArrayList<String> temp=dic.get(i);
            if ((temp.contains(a) && temp.contains(b))|| a.equals(b)){
                return true;
            }
        }
        return false;
    }

    //读取知识图谱三元组，融合后返回json字符串
    public ResponseVO getFusion(DataVO dataVO) throws IOException {
        //读取内容
        //第一个知识图谱
        String strTmp1 = dataVO.getDataString();
        ArrayList<ArrayList<String>> kg1=new ArrayList<>();
        ArrayList<ArrayList<String>> kg1_nodes=new ArrayList<>();

        //第二个知识图谱
        String strTmp2 = dataVO.getNextData();
        ArrayList<ArrayList<String>> kg2=new ArrayList<>();
        ArrayList<ArrayList<String>> kg2_nodes=new ArrayList<>();


        JsonObject jsonContainer1=new JsonParser().parse(strTmp1).getAsJsonObject();
        JsonArray nodes1=(JsonArray) jsonContainer1.get("nodes");
        JsonArray links1= (JsonArray) jsonContainer1.get("links");

        for (int j=0;j<nodes1.size();j++){
            ArrayList<String> temp=new ArrayList<>();
            JsonObject node= (JsonObject) nodes1.get(j);
            String category=node.get("category").toString().replaceAll("\"","");
            String name=node.get("name").toString().replaceAll("\"","");
            temp.add(category);
            temp.add(name);
            kg1_nodes.add(temp);
        }
        for (int j=0;j<links1.size();j++){
            ArrayList<String> temp=new ArrayList<>();
            JsonObject link= (JsonObject) links1.get(j);
            String source=link.get("source").toString().replaceAll("\"","");
            String target=link.get("target").toString().replaceAll("\"","");
            String name=link.get("name").toString().replaceAll("\"","");
            temp.add(source);
            temp.add(target);
            temp.add(name);
            kg1.add(temp);
        }

        JsonObject jsonContainer2=new JsonParser().parse(strTmp2).getAsJsonObject();
        JsonArray nodes2=(JsonArray) jsonContainer2.get("nodes");
        JsonArray links2= (JsonArray) jsonContainer2.get("links");

        for (int j=0;j<nodes2.size();j++){
            ArrayList<String> temp=new ArrayList<>();
            JsonObject node= (JsonObject) nodes2.get(j);
            String category=node.get("category").toString().replaceAll("\"","");
            String name=node.get("name").toString().replaceAll("\"","");
            temp.add(category);
            temp.add(name);
            kg2_nodes.add(temp);
        }
        for (int j=0;j<links2.size();j++){
            ArrayList<String> temp=new ArrayList<>();
            JsonObject link= (JsonObject) links2.get(j);
            String source=link.get("source").toString().replaceAll("\"","");
            String target=link.get("target").toString().replaceAll("\"","");
            String name=link.get("name").toString().replaceAll("\"","");
            temp.add(source);
            temp.add(target);
            temp.add(name);
            kg2.add(temp);
        }

        //合并三元组
        ArrayList<ArrayList<String>> links_result= new ArrayList<>();
        links_result=(ArrayList<ArrayList<String>>) kg1.clone();

        for (int j=0;j<kg2.size();j++){
            ArrayList<String> linkB=kg2.get(j);  //需要合并的节点
            int index1=-1;      //记录kg1中相似的主节点序号
            int index2=-1;      //记录kg1中相同的副节点序号
            for (int i=0;i<kg1.size();i++){
                ArrayList<String> linkA=kg1.get(i);
                if (fuse(linkA.get(0),linkB.get(0))){
                    //找到主节点相似
                    index1=i;
                    if (linkA.get(1).equals(linkB.get(1))){
                        //找到副节点相同
                        index2=i;
                    }
                }
            }
            ArrayList<String> change=new ArrayList<>();

            if (index1==-1){
                //与kg1中的任何一个关系不相似
                links_result.add(linkB);
            }
            else if (index2==-1){
                //主节点相似但是没有相同的父节点
                change.add(kg1.get(index1).get(0));
                change.add(linkB.get(1));
                change.add(linkB.get(2));
                links_result.add(change);
            }
            else{
                //主节点相似且父节点相同
                continue;
            }

        }

        //合并节点
        ArrayList<ArrayList<String>> nodes_result= new ArrayList<>();
        nodes_result=(ArrayList<ArrayList<String>>) kg1_nodes.clone();
        for (int j=0;j<kg2_nodes.size();j++){
            boolean flag=true;
            ArrayList<String> nodeB=kg2_nodes.get(j);
            for (int i=0;i<kg1_nodes.size();i++){
                ArrayList<String> nodeA=kg1_nodes.get(i);
                if (fuse(nodeA.get(1),nodeB.get(1))){
                    //找到相似的了
                    flag=false;
                }
            }
            if (flag)
                nodes_result.add(nodeB);
        }

        //生成知识图谱
        JsonObject jsonContainer=new JsonObject();
        jsonContainer.addProperty("title","知识图谱");
        JsonArray nodes=new JsonArray();
        JsonArray links=new JsonArray();
        JsonObject frontSize=new JsonObject();
        for (int i=0;i<nodes_result.size();i++){
            JsonObject node=new JsonObject();
            node.addProperty("name",nodes_result.get(i).get(1));
            node.addProperty("des","nodedes"+(i+1));
            String category=nodes_result.get(i).get(0);
            if (category.equals("0")) {
                node.addProperty("symbol", "circle");
                node.addProperty("symbolSize",40);
                node.addProperty("type","highlight");
                frontSize.addProperty("frontSize",15);
            }
            else if (category.equals("1")||category.equals("4")) {
                node.addProperty("symbol", "triangle");
                node.addProperty("symbolSize",30);
                frontSize.addProperty("frontSize",12);
            }
            else if (category.equals("2")||category.equals("5")) {
                node.addProperty("symbol", "rectangle");
                node.addProperty("symbolSize",30);
                frontSize.addProperty("frontSize",12);
            }
            else {
                node.addProperty("symbol", "diamond");
                node.addProperty("symbolSize",30);
                frontSize.addProperty("frontSize",12);
            }
            JsonObject color=new JsonObject();
            node.add("itemStyle",color);


            node.add("label",frontSize);
            if (category.equals("0")) {
                node.addProperty("category",0);
            }
            else if (category.equals("1")) {
                node.addProperty("category",1);
            }
            else if (category.equals("2")) {
                node.addProperty("category",2);
            }
            else if (category.equals("3")) {
                node.addProperty("category",3);
            }
            else if (category.equals("4")) {
                node.addProperty("category",4);
            }
            else if (category.equals("5")) {
                node.addProperty("category",5);
            }
            else {
                node.addProperty("category",6);
            }
            nodes.add(node);
        }
        for (int i=0;i<links_result.size();i++){
            JsonObject link=new JsonObject();
            link.addProperty("source",links_result.get(i).get(0));
            link.addProperty("target",links_result.get(i).get(1));
            link.addProperty("name",links_result.get(i).get(1));
            link.addProperty("des","link"+(i+1)+"des");

            links.add(link);
        }
        jsonContainer.add("nodes",nodes);
        jsonContainer.add("links",links);
        jsonContainer.addProperty("isChartFixed",false);
        JsonArray potions=new JsonArray();
        jsonContainer.add("potions",potions);

        System.out.println(jsonContainer);
        String jsonString=jsonContainer.toString();
        return ResponseVO.success(jsonString);
    }
}
