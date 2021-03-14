function setCookie(name, email) {
    let cookie = "name" + "=" + name + ";";
    cookie = cookie + "email" + "=" + email+ ";";
    document.cookie = cookie;
}
function getCookie(cname) {
    let name = cname + "=";
    let cookie = document.cookie.split(';');
    for(let i = 0, len = cookie.length; i < len; i++) {
        let c = cookie[i].trim();
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function clearCookie() {
    setCookie("illegal","illegal@a.com")
}