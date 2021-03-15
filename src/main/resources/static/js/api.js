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

const addAccountAPI=function(usn, eml, psw) {
    $.ajax({
        type:"post",
        url:"/api/user/register",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        data:JSON.stringify({
            name:usn,
            mail:eml,
            password:psw,
        }),
        success:function(response){
            console.log(response.msg);
            if (response.msg==="end") {
                setCookie(usn, eml);
                setTimeout("location.href=\"home.html\";", 100);
            }
        }
    });
};

const sendCodeAPI=function(eml, cdk) {
    $.ajax({
        type:"post",
        url:"/api/user/code",
        contentType: "application/json;charset=utf-8",
        data:{
            "email":eml,
            "code":cdk
        },
        dataType: "json",
        success:function(response){
            if (response.msg==="success") {
                alert("验证码已发送，请检查邮箱！");
            }else {
                alert("验证码发送失败，请检查网络情况！");
            }
        }
    });
};

const verifyAccount=function(eml,psw) {
    $.ajax({
        type:"post",
        url:"/api/user/login",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
            mail:eml,
            password:psw,
        }),
        dataType: "json",
        success:function(response){
            console.log(response.msg);
            if (response.msg!=="") {
                setCookie(response.msg, eml);
                setTimeout("location.href=\"home.html\";", 100);
            }else{
                alert("登录失败，请检查账号密码或重新点击登录！")
            }
        }
    });
};