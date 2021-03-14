function saveChartAPI(nodeList,linkList) {
    $.ajax({
        type: "POST",
        url: "/api/chart/saveChart",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        data: JSON.stringify({nodeList:nodeList,linkList:linkList}),
         success: function (response) {
            console.log(response.msg);
        },
});
}