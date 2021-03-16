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
    console.log({usn,eml,psw})
    $.ajax({
        type:"post",
        url:"/api/user/register",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        data:JSON.stringify({
            username:usn,
            email:eml,
            password:psw,
        }),
        success:function(response){
            console.log(response.msg);
            if (response.msg==="register success") {
                setCookie(usn, eml,600000);
                setTimeout("location.href=\"index\";", 100);
            }else if (response.msg==="Account exist"){
                alert("该邮箱已被使用！")
            }else{
                alert("注册失败！请检查网络！")
            }
        }
    });
};

const sendCodeAPI=function(eml, cdk) {
    $.ajax({
        type:"post",
        url:"/api/user/code",
        contentType: "application/json;charset=utf-8",
        data:JSON.stringify({
            email:eml,
            code:cdk,
        }),
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
            email:eml,
            password:psw,
        }),
        dataType: "json",
        success:function(response){
            console.log(response.msg);
            if (response.msg!=="login failure") {
                setCookie(response.msg, eml,600000);
                setTimeout("location.href=\"index\";", 100);
            }else{
                alert("登录失败，请检查账号密码或重新登录！")
            }
        }
    });
};