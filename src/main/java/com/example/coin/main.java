package com.example.coin;

import java.io.*;
import java.util.ArrayList;
import com.example.coin.serviceImpl.KgServiceImpl;

import java.util.Collections;
import java.util.Random;

import com.example.coin.service.KgService;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

class Main {
    public static ArrayList<ArrayList<String>> dic=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getAnswer();
    }
    //生成知识图谱
    public static void getKg() {
        Process proc;
        boolean change=false;
        int numOfNodes=0;
        int numOfLinks=0;
        JsonObject jsonContainer=new JsonObject();
        jsonContainer.addProperty("title","知识图谱");
        JsonArray nodes=new JsonArray();
        JsonArray links=new JsonArray();
        try {
//            proc = Runtime.getRuntime().exec("src\\main\\resources\\kg\\dist/main.exe "+str2);
//            proc = Runtime.getRuntime().exec("python36 src\\main\\resources\\kg\\main.py "+str2);
            proc = Runtime.getRuntime().exec("python36 src\\main\\resources\\kg\\makeKnowledge.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));
            String line = null;
            Random r=new Random(1);
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if (line.equals("links")){
                    change=true;
                    continue;
                }
                if (!change){
                    numOfNodes++;
                    JsonObject node=new JsonObject();
                    String[] object=line.split(" ");
                    node.addProperty("name",object[1]);
                    node.addProperty("des","nodedes"+numOfNodes);

                    JsonObject frontSize=new JsonObject();
                    if (object[0].equals("0")) {
                        node.addProperty("symbol", "circle");
                        node.addProperty("symbolSize",40);
                        node.addProperty("type","highlight");
                        frontSize.addProperty("frontSize",15);
                    }
                    else if (object[0].equals("2")||object[0].equals("6")||object[0].equals("9")) {
                        node.addProperty("symbol", "triangle");
                        node.addProperty("symbolSize",30);
                        frontSize.addProperty("frontSize",12);
                    }
                    else if (object[0].equals("1")||object[0].equals("3")||object[0].equals("5")||object[0].equals("7")||object[0].equals("8")) {
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
                    if (object[0].equals("0")) {
                        node.addProperty("category",0);
                    }
                    else if (object[0].equals("1")) {
                        node.addProperty("category",1);
                    }
                    else if (object[0].equals("2")) {
                        node.addProperty("category",2);
                    }
                    else if (object[0].equals("3")) {
                        node.addProperty("category",3);
                    }
                    else if (object[0].equals("4")) {
                        node.addProperty("category",4);
                    }
                    else if (object[0].equals("5")) {
                        node.addProperty("category",5);
                    }
                    else if (object[0].equals("6")) {
                        node.addProperty("category",6);
                    }
                    else if (object[0].equals("7")) {
                        node.addProperty("category",7);
                    }
                    else if (object[0].equals("8")) {
                        node.addProperty("category",8);
                    }
                    else if (object[0].equals("9")) {
                        node.addProperty("category",9);
                    }
                    else if (object[0].equals("10")) {
                        node.addProperty("category",10);
                    }
                    else {
                        node.addProperty("category",11);
                    }
                    nodes.add(node);
                }
                else{
                    numOfLinks++;
                    JsonObject link=new JsonObject();
                    String[] object=line.split(" ");
                    link.addProperty("source",object[1]);
                    link.addProperty("target",object[2]);
                    link.addProperty("name",object[3]);
                    link.addProperty("des","link"+numOfLinks+"des");

                    links.add(link);
                }
            }
            jsonContainer.add("nodes",nodes);
            jsonContainer.add("links",links);
            jsonContainer.addProperty("isChartFixed",false);
            JsonArray potions=new JsonArray();
            jsonContainer.add("potions",potions);
            System.out.println(jsonContainer);
            //输出json文件
            String jsonString=jsonContainer.toString();
            File file=new File("src\\main\\resources\\kg\\data/target.json");
            if (file.exists()){
                file.delete();
            }
            file.createNewFile();
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();

            in.close();
            int res=proc.waitFor();
            System.out.print(res);

            BufferedReader ine = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String linee = null;
            while((linee = ine.readLine()) != null){
                System.out.println(linee);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    //读取知识图谱进行融合的测试,传入两个字符串
    public static void getFusion() throws IOException {
        KgServiceImpl kgService=new KgServiceImpl();
        //读取内容
        //第一个知识图谱
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream("src\\main\\resources\\kg\\data\\target4.json")));
        String strTmp1 = buffReader.readLine();
        ArrayList<ArrayList<String>> kg1_links=new ArrayList<>();
        ArrayList<ArrayList<String>> kg1_nodes=new ArrayList<>();

        //第二个知识图谱
        buffReader = new BufferedReader(new InputStreamReader(new FileInputStream("src\\main\\resources\\kg\\data\\target5.json")));
        String strTmp2 = buffReader.readLine();
        ArrayList<ArrayList<String>> kg2_links=new ArrayList<>();
        ArrayList<ArrayList<String>> kg2_nodes=new ArrayList<>();

        buffReader.close();

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
            kg1_links.add(temp);
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
            kg2_links.add(temp);
        }

        //合并三元组
        ArrayList<ArrayList<String>> links_result= new ArrayList<>();
        links_result=(ArrayList<ArrayList<String>>) kg1_links.clone();

        for (int j=0;j<kg2_links.size();j++){
            ArrayList<String> linkB=kg2_links.get(j);  //需要合并的节点
            int index1=-1;      //记录kg1中相似的主节点序号
            int index2=-1;      //记录kg1中相同的副节点序号
            for (int i=0;i<kg1_links.size();i++){
                ArrayList<String> linkA=kg1_links.get(i);
                if (kgService.fuse(linkA.get(0),linkB.get(0))){
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
                change.add(kg1_links.get(index1).get(0));
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
                if (kgService.fuse(nodeA.get(1),nodeB.get(1))){
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
            else if (category.equals("2")||category.equals("6")||category.equals("9")) {
                node.addProperty("symbol", "triangle");
                node.addProperty("symbolSize",30);
                frontSize.addProperty("frontSize",12);
            }
            else if (category.equals("1")||category.equals("3")||category.equals("5")||category.equals("7")||category.equals("8")) {
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
            else if (category.equals("6")) {
                node.addProperty("category",6);
            }
            else if (category.equals("7")) {
                node.addProperty("category",7);
            }
            else if (category.equals("8")) {
                node.addProperty("category",8);
            }
            else if (category.equals("9")) {
                node.addProperty("category",9);
            }
            else if (category.equals("10")) {
                node.addProperty("category",10);
            }
            else {
                node.addProperty("category",11);
            }
            nodes.add(node);
        }
        for (int i=0;i<links_result.size();i++){
            JsonObject link=new JsonObject();
            link.addProperty("source",links_result.get(i).get(0));
            link.addProperty("target",links_result.get(i).get(1));
            link.addProperty("name",links_result.get(i).get(2));
            link.addProperty("des","link"+(i+1)+"des");

            links.add(link);
        }
        jsonContainer.add("nodes",nodes);
        jsonContainer.add("links",links);
        jsonContainer.addProperty("isChartFixed",false);
        JsonArray potions=new JsonArray();
        jsonContainer.add("potions",potions);
        String jsonString=jsonContainer.toString();
        System.out.println(jsonString);

        File file=new File("src\\main\\resources\\kg\\data/target7.json");
        if (file.exists()){
            file.delete();
        }
        file.createNewFile();
        Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        write.write(jsonString);
        write.flush();
        write.close();
    }

    public static void getAnswer() throws IOException {
        String questions="";

        //读取生成知识图谱三元组
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream("src\\main\\resources\\kg\\data\\target1.json")));
        String strTmp1 = buffReader.readLine();
        ArrayList<ArrayList<String>> kg=new ArrayList<>();
        buffReader.close();
        JsonObject jsonContainer1=new JsonParser().parse(strTmp1).getAsJsonObject();
        JsonArray links= (JsonArray) jsonContainer1.get("links");
        for (int j=0;j<links.size();j++){
            ArrayList<String> temp=new ArrayList<>();
            JsonObject link= (JsonObject) links.get(j);
            String source=link.get("source").toString().replaceAll("\"","");
            String target=link.get("target").toString().replaceAll("\"","");
            String name=link.get("name").toString().replaceAll("\"","");
            temp.add(source);
            temp.add(target);
            temp.add(name);
            kg.add(temp);
        }
        System.out.println(kg);

        //读取生成字典
        if(dic.size()==0) {
            buffReader = new BufferedReader(new InputStreamReader(new FileInputStream("src\\main\\resources\\kg\\data\\similarDic.txt")));
            String strTmp = "";
            while ((strTmp = buffReader.readLine()) != null) {
                String [] strTmpList=strTmp.split(" ");
                ArrayList<String> temp=new ArrayList<>();
                Collections.addAll(temp,strTmpList);
                dic.add(temp);
            }
        }
        System.out.println(dic);

    }
}
