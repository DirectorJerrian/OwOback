var regName = document.getElementById("t02Name");
var regMail = document.getElementById("t02Mail");
var regCode = document.getElementById("t02Code");
var regPassword = document.getElementById("t02Psw");
var regPassword2 = document.getElementById("t02Psw2");
var regButton = document.getElementById("clickToRegister");
var regGetCode=document.getElementById("t02SendCode")
var rightCode = "";
var newCode=function () {
    rightCode="";
    const all="0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    for(var i = 1;i <= 6;i++){
        const num = Math.floor(Math.random()*82);
        rightCode += all[num];
    }
};
var jsName=regName.value,jsMail=regMail.value,jsCode=regCode.value,jsPassword=regPassword.value,jsPassword2=regPassword2.value;
const addAccount=function(usn,eml,psw) {
    $.ajax({
        type:"post",
        url:"http://localhost/api/user/register",
        data:JSON.stringify({
            name:usn,
            mail:eml,
            password:psw,
        }),
        success:function(data){
            console.log(data);
            if (data==="end") {
                setCookie(usn, eml);
                setTimeout("location.href=\"home.html\";", "100");
            }
        }
    });
};
const sendCode=function(eml,cdk) {
    $.ajax({
        type:"post",
        url:"http://localhost/api/user/code",
        data:{
            "email":eml,
            "code":cdk
        },
        success:function(data){
            if (data==="success") {
                alert("验证码已发送，请检查邮箱！");
            }else {
                alert("验证码发送失败，请检查网络情况！");
            }
        }
    });
};
const registerClicked=function () {
    jsName=regName.value,jsMail=regMail.value,jsCode=regCode.value,jsPassword=regPassword.value,jsPassword2=regPassword2.value;
    var pureNum = new RegExp("^[0-9]*$");
    if(regName.validity.valueMissing){
        alert("请输入用户名！");
    }else if(regMail.validity.valueMissing){
        alert("请输入邮箱！");
    }else if(regMail.validity.typeMismatch){
        alert("请输入正确的邮箱！");
    }else if(regCode.validity.valueMissing){
        alert("请输入验证码！");
    }else if(jsCode!==rightCode){
        alert("验证码错误！");
    }else if(regPassword.validity.valueMissing){
        alert("请输入密码！");
    }else if(regPassword2.validity.valueMissing){
        alert("请再次输入密码！");
    }else if(jsPassword!==jsPassword2){
        alert("两次密码不一致！");
    }else{
        addAccount(jsName,jsMail,jsPassword);
    }
};
const codeClicked=function () {
    jsMail=regMail.value;
    if(regMail.validity.valueMissing){
        alert("请输入邮箱！");
    }else if(regMail.validity.typeMismatch){
        alert("请输入正确的邮箱！");
    }else{
        newCode();
        sendCode(jsMail,rightCode);
    }
};
if (regButton.addEventListener)
    regButton.addEventListener("click", registerClicked, false);
else if (regButton.attachEvent)
    regButton.attachEvent('onclick', registerClicked());
if (regGetCode.addEventListener)
    regGetCode.addEventListener("click", codeClicked, false);
else if (regGetCode.attachEvent)
    regGetCode.attachEvent('onclick', codeClicked());
console.log(rightCode);
newCode();
console.log(rightCode);
console.log(jsCode);
