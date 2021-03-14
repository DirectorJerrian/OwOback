var lgnAccount = document.getElementById("t02Account");
var lgnPassword = document.getElementById("t02Psw");
var lgnButton = document.getElementById("clickToLogin");
var js2Name,js2Mail=lgnAccount.value,js2Password=lgnPassword.value;
const verifyAccount=function(eml,psw) {
    $.ajax({
        type:"post",
        url:"http://localhost/api/user/login",
        data:JSON.stringify({
            mail:eml,
            password:psw,
        }),
        success:function(data){
            console.log(data);
            if (data!=="") {
                setCookie(data, eml);
                setTimeout("location.href=\"home.html\";", "100");
            }else{
                alert("登录失败，请检查账号密码或重新点击登录！")
            }
        }
    });
};
const loginClicked=function () {
    js2Name,js2Mail=lgnAccount.value,js2Password=lgnPassword.value;
    if(lgnAccount.validity.valueMissing){
        alert("请输入账号！");
    }else if(lgnPassword.validity.valueMissing){
        alert("请输入密码！");
    }else{
        verifyAccount(js2Mail,js2Password);
    }
};
if (lgnButton.addEventListener)
    lgnButton.addEventListener("click", loginClicked, false);
else if (lgnButton.attachEvent)
    lgnButton.attachEvent('onclick', loginClicked());