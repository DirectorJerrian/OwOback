#!/usr/bin/env python3
# coding: utf-8
import os
from triple_extraction import *
with open("test01.txt", 'r',encoding='UTF-8') as f:    #打开文件
    data = f.read()   #读取文件

extractor = TripleExtractor()
svos = extractor.triples_main(data)
for n in svos:
    if svos.count(n)>1:
        svos.remove(n)
# print('svos', svos)

all_entities=[]
for i in range(len(svos)):
    all_entities.append(svos[i][0])
    all_entities.append(svos[i][2])
all_entities=list(set(all_entities))


result="node\n"
for i in range(len(all_entities)):
    result=result+"name:"+all_entities[i]+" des:\'nodedes"+str(i)+" symbolSize:50 category:1\n"
result+="links\n"
for i in range(len(svos)):
    result = result + "source:" + svos[i][0] + " target:" + svos[i][2]
    result= result + " name:" + svos[i][1] + " des:link" + str(i) + "des\n"
print(result)
if os.path.exists("./target.json"):
        os.remove("./target.json")
f = open("./target.json", "w+")
f.write(result)
f.close()