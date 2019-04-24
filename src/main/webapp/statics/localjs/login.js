
function loginAction(){
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var identity = document.getElementById("identity").value;
    var loginBtn = document.getElementById('loginBtn');
    if (!username){
        $("#username").focus();
        alert("用户名不能为空!!");
        return false;
    }
    if (!password) {
        $("#password").focus();
        alert("密码不能为空！");

        return false;
    }
    if (!identity) {
        $("#identity").focus();
        alert("身份信息必须选择！！");
        return false;
    }
    return true;

}