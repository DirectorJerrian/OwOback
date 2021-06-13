#该程序对输出知识图谱三元组，与java程序配合使用

import csv
import random
import re
import string

import jiagu


result=""
index1=0
index2=0
array1=[[0] * 2 for i in range(20000)]
array2=[[0] * 4 for i in range(5000)]

dictlist=['所有人群','男性','女性','妇女','孕妇'
    ,'婴幼儿','婴儿','小儿','儿童','青少年','青壮年','青年','成人','成年人','成年男性','中年'
    ,'中、老年','中老年','老年人','年迈'
    ,'脑力劳动者','肥胖者','妈妈','性活跃人群','女孩']

# with open('data/disease.csv','r',encoding='ansi',newline='') as f:
with open('src\\main\\resources\\kg\\data\\disease.csv','r',encoding='ansi',newline='') as f:
    reader = csv.reader(f)
    resource = list(reader)

for i in range(1,100):
    record = resource[i]
    name = record[0].strip().strip('.')
    alias = record[1].strip().strip('.').replace(" ", "")
    part=record[2].strip().strip('.')
    age=record[3].strip().strip('.')
    infection=record[4].strip()
    insurance=record[5].strip()
    department=record[6].strip().strip('.').replace("  "," ")
    complication=record[9].strip().strip('.')
    period=record[12].strip().strip('.')


    # 第一种知识图谱，1000种，包含疾病名字、发病部位、发病人群、传染性、所属科
    # if(name!=''):
    #     array1[index1][0]=0
    #     array1[index1][1]=name
    #     index1+=1
    #     #发病部位
    #     if (part != ''):
    #         partlist = re.split(r' ', part)
    #         if (len(partlist) > 0):
    #             for j in range(len(partlist)):
    #                 singlePart = partlist[j]
    #                 if (singlePart != ''):
    #                     array1[index1][0]=1
    #                     array1[index1][1]=singlePart
    #
    #                     array2[index2][0]=1
    #                     array2[index2][1]=name
    #                     array2[index2][2]=singlePart
    #                     array2[index2][3]= "发病部位"
    #
    #                     index1+=1
    #
    #                     index2+=1
    #     #发病人群
    #     if (age != ''):
    #         flag = True
    #         first = True
    #         for j in range(len(dictlist)):
    #             if (dictlist[j] in age):
    #                 singleAge = dictlist[j]
    #                 array1[index1][0] = 2
    #                 array1[index1][1]=singleAge
    #
    #                 array2[index2][0] = 2
    #                 array2[index2][1]=name;
    #                 array2[index2][2]=singleAge;
    #                 array2[index2][3]= "发病人群"
    #
    #                 index1+=1
    #
    #                 index2+=1
    #
    #     #传染性
    #     if(infection!=''):
    #         array1[index1][0] = 4
    #         array1[index1][1]=infection
    #         array2[index2][0]=4
    #         array2[index2][1]=name
    #         array2[index2][2]=infection
    #         array2[index2][3]= "传染性"
    #         index1+=1
    #         index2+=1
    #
    #     #所属科
    #     if (department != ''):
    #         departmentlist = re.split(r' ', department)
    #         for j in range(len(departmentlist) - 1, -1, -1):
    #             if (departmentlist[j][-1] != "科"):
    #                 del departmentlist[j]
    #         if (len(departmentlist) > 0):
    #             for j in range(len(departmentlist)):
    #                 singleDepartment = departmentlist[j]
    #                 if (singleDepartment != ''):
    #                     array1[index1][0] = 3
    #                     array1[index1][1]=singleDepartment
    #
    #                     array2[index2][0] = 3
    #                     array2[index2][1]=name
    #                     array2[index2][2]=singleDepartment
    #                     array2[index2][3]= "所属科"
    #
    #                     index1+=1
    #
    #                     index2+=1



    # 第二种知识图谱，1000种，包含疾病别名、传染性、并发症、治疗时间、所属科
    if(alias!=''):
        aliaslist = re.split(r'[,，、]', alias)
        # name=random.choice (aliaslist)
        if(len(aliaslist)>1):
            name=aliaslist[0]
        else:
            name=aliaslist[0]
        array1[index1][0]=0
        array1[index1][1] = name
        index1 += 1

        #传染性
        if(infection!=''):
            array1[index1][0] = 4
            array1[index1][1]=infection
            array2[index2][0]=4
            array2[index2][1]=name
            array2[index2][2]=infection
            array2[index2][3]= "传染性"
            index1+=1
            index2+=1


        #并发症
        if(complication!=''):
            complicationlist = re.split(r' ', complication)
            for j in range(len(complicationlist) - 1, -1, -1):
                if (complicationlist[j]=='[详细]'):
                    del complicationlist[j]
            if (len(complicationlist)>0):
                for j in range(len(complicationlist)):
                    singleComplication = complicationlist[j]
                    if (singleComplication != ''):
                        array1[index1][0] = 5
                        array1[index1][1]=singleComplication
                        array2[index2][0] = 5
                        array2[index2][1] = name
                        array2[index2][2] = singleComplication
                        array2[index2][3] = "并发症"
                        index1+=1
                        index2+=1

        #治疗时间
        if(period!=''):
            array1[index1][0] = 6
            array1[index1][1]=period
            array2[index2][0] = 6
            array2[index2][1] = name
            array2[index2][2] = period
            array2[index2][3] = "治疗时间"
            index1+=1
            index2+=1

        #所属科
        if (department != ''):
            departmentlist = re.split(r' ', department)
            for j in range(len(departmentlist) - 1, -1, -1):
                if (departmentlist[j][-1] != "科"):
                    del departmentlist[j]
            if (len(departmentlist) > 0):
                for j in range(len(departmentlist)):
                    singleDepartment = departmentlist[j]
                    if (singleDepartment != ''):
                        array1[index1][0] = 3
                        array1[index1][1]=singleDepartment

                        array2[index2][0] = 3
                        array2[index2][1]=name
                        array2[index2][2]=singleDepartment
                        array2[index2][3]= "所属科"

                        index1+=1

                        index2+=1

for i in range(19999,-1,-1):
    for j in range(i-1,-1,-1):
        if(array1[i][0]==array1[j][0] and array1[i][1]==array1[j][1]):
            del array1[i]
            break
for i in range(len(array1)-1):
    print(str(array1[i][0]) + " " + str(array1[i][1]))

print("links")
for i in range(index2):
    print(str(array2[i][0]) + " " + str(array2[i][1]) + " " + str(array2[i][2]) + " " + str(array2[i][3]))