import re
import kg.entity
import kg.relation
import json
import kg.clear
import json

#选择语言
language="zh"
with open("test01.txt", 'r',encoding='UTF-8') as f:    #打开文件
    data = f.read()   #读取文件

#根据语言去除乱码
if language=="zh":
    #中文文本去除乱码
    sentence=kg.clear.split_by_sign(data)
    sentence=[x for x in sentence if len(x)>15]
    # data=data.replace("\u3000","")
    # data=data.replace(" ","")
    #
    # #以“。”和"\n"进行分割
    # sentence=re.split('。|\n',data)
    # sentence=[x for x in sentence if x!='' ]
else:
    #英文文本去除乱码
    new_data=kg.clear.clean_text(data)
    sentence=new_data.split(".")
    sentence = [x for x in sentence if len(x) > 5]

# for i in sentence:
#     doc=nlp(i)
#     for tok in doc:
#         print(tok.text, "...", tok.dep_)

#entity_pairs：存储实体对，relation:存储关系
entity_pairs=[]
relation_pairs=[]

#读取实体和关系
for string in sentence:
    entity_pairs.append(kg.entity.get_entities(string,language))
    relation_pairs.append(kg.relation.get_relation(string, language))

i=0
length=len(entity_pairs)
while i<length:
    if entity_pairs[i][0]=="(" or entity_pairs[i][1]=="(" or entity_pairs[i][0]==")" or entity_pairs[i][0]==")":
        del entity_pairs[i]
        del relation_pairs[i]
        i-=1
        length-=1
    i+=1

#获取所有实体
all_entities=[]
for i in range(length):
    all_entities.append(entity_pairs[i][0])
    all_entities.append(entity_pairs[i][1])
all_entities=list(set(all_entities))

#所有实体存储在all_entities中，三元组中的关系存储在relation_pairs中，对象存储在entity_pairs中
result="{data:["
for i in range(len(all_entities)):
    result=result+"{name:\'"+all_entities[i]+"\',des:\'nodedes"+str(i)+"\',symbolSize:50,category:1,}"
    if i!=len(all_entities)-1:
        result+=","
result+="],links:["
for i in range(length):
    result = result + "{source:\'" + entity_pairs[i][0] + "\',target:\'" + entity_pairs[i][1]
    result= result + "\',name:\'" + relation_pairs[i] + "\',des:\'link" + str(i) + "des\'}"
    if i!=length-1:
        result+=","
result+="]}"
print(result)
# info1={}
# data=json.loads(json.dumps(info1))
# for i in range(len(all_entities)):
#     info={}
#     temp=json.loads(json.dumps(info))
#     temp["name"]=all_entities[i]
#     temp["des"]="nodedes"+str(i)
#     temp["symbolSize"]=50
#     temp["category"]=1
#     print(temp)