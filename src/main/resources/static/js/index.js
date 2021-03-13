//实体数据
var data=[{
    name: 'node01',
    des: 'nodedes01',
    symbolSize: 70,
    category: 0,
}, {
    name: 'node02',
    des: 'nodedes02',
    symbolSize: 50,
    category: 1,
}, {
    name: 'node03',
    des: 'nodedes3',
    symbolSize: 50,
    category: 1,
}, {
    name: 'node04',
    des: 'nodedes04',
    symbolSize: 50,
    category: 1,
}, {
    name: 'node05',
    des: 'nodedes05',
    symbolSize: 50,
    category: 1,
}];
//关系数据
var links= [{
    source: 'node01',
    target: 'node02',
    name: 'link01',
    des: 'link01des'
}, {
    source: 'node01',
    target: 'node03',
    name: 'link02',
    des: 'link02des'
}, {
    source: 'node01',
    target: 'node04',
    name: 'link03',
    des: 'link03des'
}, {
    source: 'node01',
    target: 'node05',
    name: 'link04',
    des: 'link05des'
}];
//种类，存访各个种类的名字，与实体中的种类一一对应；
var categories = [];
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
                show: true
            },
            // 保存为图片
            saveAsImage: {
                show: true
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
        alert("该名字已经存在");
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
//图标展示，将实体和链接重新赋值并展示
function showChart(){
    option.series[0].data=data;
    option.series[0].links=links;
    myChart.setOption(option);
}
//点击事件
myChart.on('click',function (param) {
    console.log(param);
    var beforeName=param.name;
    replaceNodeName(beforeName,'clicked');
    showChart();
})

//
//
//////////////////////////////////////////////////////////////此段代码用于测试，真实实例中，需要根据实体数据赋予种类名字
for (var i = 0; i < 2; i++) {
    categories[i] = {
        name: '类目' + i
    };
}
console.log(123);
option.series[0].data=data;
option.series[0].links=links;
myChart.setOption(option);
//////////////////////////////////////////////////////////////
//
//

