var lgnAccount = document.getElementById("t02Account");
var lgnPassword = document.getElementById("t02Psw");
var lgnButton = document.getElementById("clickToLogin");
var js2Name,js2Mail=lgnAccount.value,js2Password=lgnPassword.value;

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