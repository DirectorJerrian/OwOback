//package com.example.coin;
//
//import java.io.*;
//import java.util.Random;
//
//import com.example.coin.service.KgService;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import org.springframework.stereotype.Service;
//
//class Main {
//    public static void main(String[] args) {
//        String str1 = "环境很好，位置独立性很强，比较安静很切合店名，半闲居，偷得半日闲。点了比较经典的菜品，味道果然不错！烤乳鸽，超级赞赞赞，脆皮焦香，肉质细嫩，超好吃。艇仔粥料很足，香葱自己添加，很贴心。金钱肚味道不错，不过没有在广州吃的烂，牙口不好的慎点。凤爪很火候很好，推荐。最惊艳的是长寿菜，菜料十足，很新鲜，清淡又不乏味道，而且没有添加调料的味道，搭配的非常不错！";
//        String str2 = "近日，一条男子高铁吃泡面被女乘客怒怼的视频引发热议。女子情绪激动，言辞激烈，大声斥责该乘客，称高铁上有规定不能吃泡面，质问其“有公德心吗”“没素质”。视频曝光后，该女子回应称，因自己的孩子对泡面过敏，曾跟这名男子沟通过，但对方执意不听，她才发泄不满，并称男子拍视频上传已侵犯了她的隐私权和名誉权，将采取法律手段。12306客服人员表示，高铁、动车上一般不卖泡面，但没有规定高铁、动车上不能吃泡面。高铁属于密封性较强的空间，每名乘客都有维护高铁内秩序，不破坏该空间内空气质量的义务。这也是乘客作为公民应当具备的基本品质。但是，在高铁没有明确禁止食用泡面等食物的背景下，以影响自己或孩子为由阻挠他人食用某种食品并厉声斥责，恐怕也超出了权利边界。当人们在公共场所活动时，不宜过分干涉他人权利，这样才能构建和谐美好的公共秩序。一般来说，个人的权利便是他人的义务，任何人不得随意侵犯他人权利，这是每个公民得以正常工作、生活的基本条件。如果权利可以被肆意侵犯而得不到救济，社会将无法运转，人们也没有幸福可言。如西谚所说，“你的权利止于我的鼻尖”，“你可以唱歌，但不能在午夜破坏我的美梦”。无论何种权利，其能够得以行使的前提是不影响他人正常生活，不违反公共利益和公序良俗。超越了这个边界，权利便不再为权利，也就不再受到保护。在“男子高铁吃泡面被怒怼”事件中，初一看，吃泡面男子可能侵犯公共场所秩序，被怒怼乃咎由自取，其实不尽然。虽然高铁属于封闭空间，但与禁止食用刺激性食品的地铁不同，高铁运营方虽然不建议食用泡面等刺激性食品，但并未作出禁止性规定。由此可见，即使食用泡面、榴莲、麻辣烫等食物可能产生刺激性味道，让他人不适，但是否食用该食品，依然取决于个人喜好，他人无权随意干涉乃至横加斥责。这也是此事件披露后，很多网友并未一边倒地批评食用泡面的男子，反而认为女乘客不该高声喧哗。现代社会，公民的义务一般分为法律义务和道德义务。如果某个行为被确定为法律义务，行为人必须遵守，一旦违反，无论是受害人抑或旁观群众，均有权制止、投诉、举报。违法者既会受到应有惩戒，也会受到道德谴责，积极制止者则属于应受鼓励的见义勇为。如果有人违反道德义务，则应受到道德和舆论谴责，并有可能被追究法律责任。如在公共场所随地吐痰、乱扔垃圾、脱掉鞋子、随意插队等。此时，如果行为人对他人的劝阻置之不理甚至行凶报复，无疑要受到严厉惩戒。当然，随着社会的发展，某些道德义务可能上升为法律义务。如之前，很多人对公共场所吸烟不以为然，烟民可以旁若无人地吞云吐雾。现在，要是还有人不识时务地在公共场所吸烟，必然将成为众矢之的。再回到“高铁吃泡面”事件，要是随着人们观念的更新，在高铁上不得吃泡面等可能产生刺激性气味的食物逐渐成为共识，或者上升到道德义务或法律义务。斥责、制止他人吃泡面将理直气壮，否则很难摆脱“矫情”，“将自我权利凌驾于他人权利之上”的嫌疑。在相关部门并未禁止在高铁上吃泡面的背景下，吃不吃泡面系个人权利或者个人私德，是不违反公共利益的个人正常生活的一部分。如果认为他人吃泡面让自己不适，最好是请求他人配合并加以感谢，而非站在道德制高点强制干预。只有每个人行使权利时不逾越边界，与他人沟通时好好说话，不过分自我地将幸福和舒适凌驾于他人之上，人与人之间才更趋于平等，公共生活才更趋向美好有序。";
//
//        Process proc;
//        boolean change=false;
//        int numOfNodes=0;
//        int numOfLinks=0;
//        JsonObject jsonContainer=new JsonObject();
//        jsonContainer.addProperty("title","知识图谱");
//        JsonArray nodes=new JsonArray();
//        JsonArray links=new JsonArray();
//        try {
////            proc = Runtime.getRuntime().exec("src\\main\\resources\\kg\\dist/main.exe "+str2);
//            proc = Runtime.getRuntime().exec("python src\\main\\resources\\kg\\main.py "+str2);
//            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            String line = null;
//            Random r=new Random(1);
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//                if (line.equals("links")){
//                    change=true;
//                    continue;
//                }
//                if (!change){
//                    numOfNodes++;
//                    JsonObject node=new JsonObject();
//                    node.addProperty("name",line);
//                    node.addProperty("des","nodedes"+numOfNodes);
//                    int ran_symbol=r.nextInt(100);
//                    if (ran_symbol<25)
//                        node.addProperty("symbol","circle");
//                    else if (ran_symbol<50)
//                        node.addProperty("symbol","triangle");
//                    else if (ran_symbol<75)
//                        node.addProperty("symbol","rectangle");
//                    else
//                        node.addProperty("symbol","diamond");
//                    node.addProperty("symbolSize",30);
//                    node.addProperty("type","highlight");
//                    JsonObject color=new JsonObject();
//                    node.add("itemStyle",color);
//                    JsonObject frontSize=new JsonObject();
//                    frontSize.addProperty("frontSize",12);
//                    node.add("label",frontSize);
//                    int ran_Category=r.nextInt(10);
//                    node.addProperty("category",ran_Category);
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
//            //输出json文件
//            String jsonString=jsonContainer.toString();
//            File file=new File("src\\main\\resources\\kg/target.json");
//            if (file.exists()){
//                file.delete();
//            }
//            file.createNewFile();
//            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
//            write.write(jsonString);
//            write.flush();
//            write.close();
//
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
