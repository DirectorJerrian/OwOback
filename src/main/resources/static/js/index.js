//实体数据
var data=[{
    name: '足球运动员',
    des: 'nodedes01',
    symbolSize: 50,
    category: 1,
}, {
    name: '皇家马德里',
    des: 'nodedes02',
    symbolSize: 50,
    category: 1,
}, {
    name: '葡萄牙',
    des: 'nodedes3',
    symbolSize: 50,
    category: 1,
}, {
    name: '运动员',
    des: 'nodedes04',
    symbolSize: 50,
    category: 1,
}, {
    name: 'C罗',
    des: 'nodedes05',
    symbolSize: 50,
    category: 0,
}, {
    name: '人物',
    des: 'nodedes06',
    symbolSize: 50,
    category: 1,
}, {
    name: '金球奖',
    des: 'nodedes07',
    symbolSize: 50,
    category: 0,
}, {
    name: '奖项',
    des: 'nodedes08',
    symbolSize: 50,
    category: 1,
}, {
    name: '《法国足球》',
    des: 'nodedes09',
    symbolSize: 50,
    category: 1,
}, {
    name: '影响力大的足球奖项评选之一',
    des: 'nodedes10',
    symbolSize: 50,
    category: 1,
}

];
//关系数据
var links= [{
    source: '足球运动员',
    target: '运动员',
    name: 'sybclass of',
    des: 'link01des'
}, {
    source: 'C罗',
    target: '足球运动员',
    name: 'instance of',
    des: 'link02des'
}, {
    source: 'C罗',
    target: '皇家马德里',
    name: '效力球队',
    des: 'link03des'
}, {
    source: 'C罗',
    target: '葡萄牙',
    name: '国籍',
    des: 'link04des'
}, {
    source: 'C罗',
    target: '运动员',
    name: 'instance of',
    des: 'link05des'
}, {
    source: '运动员',
    target: '人物',
    name: 'subclass of',
    des: 'link06des'
}, {
    source: 'C罗',
    target: '人物',
    name: 'instance of',
    des: 'link07des'
}, {
    source: 'C罗',
    target: '金球奖',
    name: '获得奖项',
    des: 'link08des'
}, {
    source: '金球奖',
    target: '奖项',
    name: 'instance of',
    des: 'link09des'
}, {
    source: '金球奖',
    target: '《法国足球》',
    name: '举办单位',
    des: 'link10des'
}, {
    source: '金球奖',
    target: '影响力大的足球奖项评选之一',
    name: '地位',
    des: 'link11des'
}

];
//种类，存访各个种类的名字，与实体中的种类一一对应；
var categories = [];
//设置种类名
for (var i = 0; i < 2; i++) {
    categories[i] = {
        name: '类目' + i
    };
}
//图的配置
option = {
    // 图的标题
    title: {
        text: '知识图谱'
    },
    // 提示框的配置
    tooltip: {
        formatter: function (x) {
            return x.data.des;
        }
    },
    // 工具箱
    toolbox: {
        // 显示工具箱
        show: true,
        feature: {
            mark: {
                show: true
            },
            // 还原
            restore: {
                show: false
            },
            // 保存为图片
            saveAsImage: {
                show: false
            }
        }
    },
    legend: [{
        // selectedMode: 'single',
        data: categories.map(function (a) {
            return a.name;
        })
    }],
    series: [{
        type: 'graph', // 类型:关系图
        layout: 'force', //图的布局，类型为力导图
        symbolSize: 40, // 调整节点的大小
        roam: true, // 是否开启鼠标缩放和平移漫游。默认不开启。如果只想要开启缩放或者平移,可以设置成 'scale' 或者 'move'。设置成 true 为都开启
        edgeSymbol: ['circle', 'arrow'],
        edgeSymbolSize: [2, 10],
        edgeLabel: {
            normal: {
                textStyle: {
                    fontSize: 20
                }
            }
        },
        force: {
            repulsion: 2500,
            edgeLength: [10, 50]
        },
        draggable: true,
        lineStyle: {
            normal: {
                width: 2,
                color: '#4b565b',
            }
        },
        edgeLabel: {
            normal: {
                show: true,
                formatter: function (x) {
                    return x.data.name;
                }
            }
        },
        label: {
            normal: {
                show: true,
                textStyle: {}
            }
        },

        // 数据
        data: [],
        links: [],
        categories: categories,
    }]
};
//图是否固定
var isFixed=false;
//表所放在网页中的地址
const chartElementId='chart';
//用于保存图的数据对象
const myChart = echarts.init(document.getElementById(chartElementId));
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
//更改实体名称
function replaceNodeName(beforeName,afterName){
    //设定的名字是否已经存在于实体当中
    if(isNodeExist(afterName)){
        chartEdit.nameExistAlarm();
    }
    //寻找目标实体修改名字
    for(var i=0;i<data.length;i++){
        if(data[i].name===beforeName){
            data[i].name=afterName;
            break;
        }
    }
    //将关系中的实体同样做修改
    for(var i=0;i<links.length;i++){
        if(links[i].source===beforeName)links[i].source=afterName;
        if(links[i].target===beforeName)links[i].target=afterName;
    }
}
//更改关系名称
function  replaceLinkName(beforeName,afterName) {
    //寻找目标关系修改名字
    for(var i=0;i<links.length;i++){
        if(links[i].name===beforeName){
            links[i].name=afterName;
            break;
        }
    }
}
//寻找是否存该名字的实体
function isNodeExist(name) {
    for(var i=0;i<data.length;i++){
        if(data[i].name===name){
            return true;
        }
    }
    return false;

}
function isLinkExist(name) {
    for(var i=0;i<links.length;i++){
        if(links[i].name===name){
            return true;
        }
    }
    return false;
}
//寻找该node名字的下标
function findNodeIndex(name){
    for(var i=0;i<data.length;i++){
        if(data[i].name===name){
            return i;
        }
    }
}
//寻找该link名字的下标
function findLinkIndex(name){
    for(var i=0;i<links.length;i++){
        if(links[i].name===name){
            return i;
        }
    }
}
//图标展示，将实体和链接重新赋值并展示
function showChart(){
    option.series[0].data=data;
    option.series[0].links=links;
    myChart.setOption(option);
}
//固定知识图谱
function fixChart(){
    isFixed=true;
    var option=myChart.getOption();
    for(var i=0;i<data.length;i++){
        option.series[0].data[i].fixed=true;
    }
    myChart.setOption(option);
}
//灵活知识图谱
function flexibleChart(){
    isFixed=false;
    var option=myChart.getOption();
    for(var i=0;i<data.length;i++){
        option.series[0].data[i].fixed=false;
    }
    myChart.setOption(option);
}
//点击事件
myChart.on('click',function (param) {

    console.log(param);

    if(param.dataType=='edge'){
        chartEdit.chosenType='link';
        chartEdit.linkForm.name=param.data.name;
        chartEdit.linkForm.des=param.data.des;
        chartEdit.linkForm.source=param.data.source;
        chartEdit.linkForm.target=param.data.target;
        chartEdit.linkName=param.data.name;
        chartEdit.linkDes=param.data.des;
        chartEdit.linkSource=param.data.source;
        chartEdit.linkTarget=param.data.target;

    }else if(param.dataType=='node'){
        chartEdit.chosenType='node';
        chartEdit.nodeForm.name=param.data.name;
        chartEdit.nodeForm.des=param.data.des;
        chartEdit.nodeForm.symbolSize=param.data.symbolSize;
        chartEdit.nodeForm.category=param.data.category;
        chartEdit.nodeName=param.data.name;
        chartEdit.nodeDes=param.data.des;
        chartEdit.nodeSymbolSize=param.data.symbolSize;
        chartEdit.nodeCategory=categories[param.data.category].name;
    }
    chartEdit.dialogVisible=true;
})
//拖拽处理
myChart.on('mouseup',function(params){
    var option=myChart.getOption();
    option.series[0].data[params.dataIndex].x=params.event.offsetX;
    option.series[0].data[params.dataIndex].y=params.event.offsetY;
    option.series[0].data[params.dataIndex].fixed=isFixed;
    myChart.setOption(option);
});
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//图谱改变函数(包括实体和关系的删除和修改),改变后重新展示图谱
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//////////////////////////////////////////////////////
//删除实体
function deleteNode(name){
    //删除data中的实体
    var nodeIndex=findNodeIndex(name);
    data.splice(nodeIndex,1);
    //删除links中包含此实体的关系
    var newLinks=[];
    for(var i=0;i<links.length;i++){
        if(links[i].source!==name && links[i].target!==name){
            newLinks.push(links[i]);
        }
    }
    links=newLinks;
    showChart();
    chartEdit.successNotice();
    return true;

}
//更改实体信息
function changeNode(name,nodeForm) {
    var nodeIndex=findNodeIndex(name);
    if(data[nodeIndex].name===nodeForm.name && data[nodeIndex].category===nodeForm.category && data[nodeIndex].des===nodeForm.des && data[nodeIndex].symbolSize===nodeForm.symbolSize){
        chartEdit.messageNotice("未作任何修改");
        return false;
    }
    //实体名字重复，但可以和自己一样
    var isOverlap=false;
    for(var i=0;i<data.length;i++){
        if(i===nodeIndex) continue;
        else if(data[i].name===nodeForm.name){
            isOverlap=true;
            break;
        }
    }
    if(isOverlap){
        chartEdit.failureAlarm("实体名称重复，请重新修改！");
        return false;
    }
    data[nodeIndex].name=nodeForm.name;
    data[nodeIndex].des=nodeForm.des;
    data[nodeIndex].symbolSize=parseInt(nodeForm.symbolSize);
    //这边要做额外修改，先不动
    data[nodeIndex].category=parseInt(nodeForm.category);
    //将关系中的实体同样做修改
    for(var i=0;i<links.length;i++){
        if(links[i].source===name)links[i].source=nodeForm.name;
        if(links[i].target===name)links[i].target=nodeForm.name;
    }
    showChart();
    chartEdit.successNotice();
    return true;
}
//创建实体
function createNode(nodeForm) {
    var name=nodeForm.name;
    var des=nodeForm.des;
    var symbolSize=parseInt(nodeForm.symbolSize);
    var category=parseInt(nodeForm.category);
    if(isNodeExist(name)){
        elementCreate.failureAlarm("实体名称重复，请重新命名！")
        return false;
    }
    var node={
        name:name,
        des:des,
        symbolSize:symbolSize,
        category:category
    }
    data.push(node);
    console.log(data);
    showChart();
    elementCreate.successNotice();
    return true;
}
//删除关系
function deleteLink(name) {
    var linkIndex=findLinkIndex(name);
    links.splice(linkIndex,1);
    showChart();
    chartEdit.successNotice();
    return true;
}
//更改关系信息
function changeLink(name,linkForm){
    var linkIndex=findLinkIndex(name);
    if(links[linkIndex].name===linkForm.name && links[linkIndex].des===linkForm.des){
        chartEdit.messageNotice("未作任何修改");
        return false;
    }
    links[linkIndex].name=linkForm.name;
    links[linkIndex].des=linkForm.des;
    showChart();
    chartEdit.successNotice();
    return true;
}
//创建关系
function createLink(linkForm){
    console.log(linkForm);
    var name=linkForm.name;
    var des=linkForm.des;
    var source=linkForm.source;
    var target=linkForm.target;
    var nodeNotExistMessage='';
    if(!isNodeExist(source)){
        nodeNotExistMessage+="起点实体不存在!\n";
    }
    if(!isNodeExist(target)){
        nodeNotExistMessage+='目标实体不存在!';
    }
    if(nodeNotExistMessage!==''){
        elementCreate.failureAlarm(nodeNotExistMessage);
        return false;
    }
    if(isLinkExist(source,target)){
        elementCreate.failureAlarm("关系已经存在");
        return false;
    }
    var link={
        name:name,
        des:des,
        source:source,
        target:target

    }
    links.push(link);
    showChart();
    elementCreate.successNotice();
    return true;
}
//
//
//////////////////////////////////////////////////////////////此段代码用于测试，真实实例中，需要根据实体数据赋予种类名字
console.log(123);
option.series[0].data=data;
option.series[0].links=links;
myChart.setOption(option);
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//数据交互函数//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
function saveChart() {
    saveChartAPI(data,links);
}
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//文件导出函数//////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//xml文件导出
function charToXMLText(){
    var res='<?xml version="1.0" encoding="utf-8" standalone="no"?>';
    res+="<chart>";
    ////////////////////
    //添加实体
    res+="<data>";
    for(var i=0;i<data.length;i++){
        res+="<node>";
        res+="<name>"+data[i].name+"</name>";
        res+="<des>"+data[i].des+"</des>";
        res+="<symbolSize>"+data[i].symbolSize+"</symbolSize>";
        res+="<category>"+data[i].category+"</category>";
        res+="</node>";
    }
    res+="</data>";
    ///////////////////
    res+="<links>";
    for(var i=0;i<links.length;i++){
        res+="<link>";
        res+="<source>"+links[i].source+"</source>";
        res+="<target>"+links[i].target+"</target>";
        res+="<name>"+links[i].name+"</name>>";
        res+="<des>"+links[i].des+"</des>";
        res+="</link>";
    }
    res+="</links>";
    //添加关系
    //////////////////
    res+="</chart>";
    return res;
}
function chartXMLDownload(){
    const XMLText=charToXMLText();
    const ele = document.createElement('a');// 创建下载链接
    ele.download ="MyChart.xml"
    ele.style.display = 'none';// 隐藏的可下载链接
    const blob = new Blob([XMLText]);
    ele.href = URL.createObjectURL(blob);
    document.body.appendChild(ele);
    ele.click();
    document.body.removeChild(ele);
}
//图片导出
function ChartImgDownload(){
    var canvas = $("#"+"chart").find("canvas").first()[0];
    var ctx = canvas.getContext('2d');
    var url=canvas.toDataURL();
    var link = document.createElement('a');
    link.href = url;
    link.download = "MyChart.png";
    link.click();
}
