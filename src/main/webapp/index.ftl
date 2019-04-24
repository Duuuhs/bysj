<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>

    <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


    <link rel="stylesheet" href="statics/localcss/login.css">
    <script type="text/javascript" src="statics/localjs/login.js"></script>



</head>
<body>
<div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">LOGIN</h3>
            <div class="col-md-9">
                <form id="Login" name="Login" action="login.do" method="post" onsubmit="return loginAction()">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="Username" id="username" name="username" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="Password" id="password" name="password" />
                    </div>
                    <!--身份-->
                    <div class="form-group">
                        <i class="fa fa-tag fa-lg"></i>
                        <select class="form-control required" id="identity" name="identity" >
                            <option value="" disabled selected class="placeholder">&nbsp;&nbsp;Identity</option>
                            <option value="S">&nbsp;&nbsp;学生</option>
                            <option value="T">&nbsp;&nbsp;教师</option>
                            <option value="DM">&nbsp;&nbsp;部门管理员</option>
                            <option value="SM">&nbsp;&nbsp;超级管理员</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="checkbox">
                            <input type="checkbox" name="remember" value="1"/>记住我
                        </label>
                    </div>
                    <div class="form-group col-md-offset-9">
                        <button type="submit" class="btn btn-success pull-right" id="loginBtn" name="loginBtn">登录</button>
                    </div>

                <form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
