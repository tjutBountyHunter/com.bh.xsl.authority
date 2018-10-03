<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:22px;">悬赏令权限管理系统</a></div>
        </div>
    </div>
</nav>

<div class="container">
    <h1 style="color:red">${param.errorMsg}</h1>
    <form id="loginForm" action="doLogin" method="post" class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="username" name="username" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="password" name="password" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="reg.html">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
    </form>
</div>
<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="layer/layer.js"></script>
<script>
    function dologin() {
        // 非空校验
        var username = $("#username").val();
        if ( username == "" ) {
            layer.msg("用户登录账号不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

            });
            return;
        }

        var password = $("#password").val();
        if ( password == "" ) {
            layer.msg("用户登录密码不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

            });
            return;
        }

        var loadingIndex = null;
        $.ajax({
            type : "POST",
            url  : "${APP_PATH}/dologin",
            data : {
                "username" : username,
                "password"  : password
            },
            beforeSend : function(){
                loadingIndex = layer.msg('处理中', {icon: 16});
            },
            success : function(data) {
                layer.close(loadingIndex);
                if (data.status == 200) {
                    window.location.href = "main";
                } else {
                    layer.msg(data.msg, {time:2000, icon:5, shift:6}, function(){

                    });
                }
            }
        });
    }
</script>
</body>
</html>
