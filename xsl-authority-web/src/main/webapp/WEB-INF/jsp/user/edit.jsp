<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="descrription" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/main.css">
    <link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">Bounty Hunter-权限管理系统</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li style="padding-top:8px;">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
                            <i class="glyphicon glyphicon-user"></i> 张三 <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
                            <li class="divider"></li>
                            <li><a href="login.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
                        </ul>
                    </div>
                </li>
                <li style="margin-left:10px;padding-top:8px;">
                    <button type="button" class="btn btn-default btn-danger">
                        <span class="glyphicon glyphicon-question-sign"></span> 帮助
                    </button>
                </li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="tree">
                <ul style="padding-left:0px;" class="list-group">
                    <li class="list-group-item tree-closed" >
                        <a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a>
                    </li>
                    <li class="list-group-item">
                        <span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span>
                        <ul style="margin-top:10px;">
                            <li style="height:40px;">
                                <a href="${APP_PATH}/user/add" style="color:red;"><i class="glyphicon glyphicon-user"></i> 用户维护</a>
                            </li>
                            <li style="height:40px;">
                                <a href="role.html"><i class="glyphicon glyphicon-certificate"></i> 角色维护</a>
                            </li>
                            <li style="height:40px;">
                                <a href="permission.html"><i class="glyphicon glyphicon-lock"></i> 许可维护</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">修改信息<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form id="userForm" role="form">
                        <div  class="form-group">
                            <label for="hunterid">猎人id</label>
                            <input type="text" class="form-control" id="hunterId" value="${user.hunterId}" placeholder="请输入猎人id">
                        </div>
                        <div class="form-group">
                            <label for="masterId">雇主id</label>
                            <input type="text" class="form-control" id="masterId" value="${user.masterId}" placeholder="请输入雇主id">
                        </div>
                        <div class="form-group">
                            <label for="name">姓名</label>
                            <input type="text" class="form-control" id="name" value="${user.name}" placeholder="请输入姓名">
                        </div>
                        <div class="form-group">
                            <label for="sex">性别</label>
                            <input type="text" class="form-control" id="sex" value="${user.sex}" placeholder="请输入性别">
                            <p class="help-block label label-warning">请输入合法的性别只允许男或女</p>
                        </div>
                        <div class="form-group">
                            <label for="phone">电话</label>
                            <input type="text" class="form-control" id="phone" value="${user.phone}" placeholder="请输入电话">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱地址</label>
                            <input type="email" class="form-control" id="email" value="${user.email}" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="button" id="updateBtn" class="btn btn-success"><i class="glyphicon glyphicon-pencil"></i> 修改</button>
                        <button type="button" id="resetBtn" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script src="${APP_PATH}/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });

        $("#resetBtn").click(function(){
            // Jquery[0] ==> DOM
            // $(DOM) ==> Jquery
            $("#userForm")[0].reset();
        });

        $("#updateBtn").click(function(){

            var hunterId = $("#hunterId").val();
            if ( hunterId == "" ) {
                layer.msg("猎人ID不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

                });
                return;
            }
            var masterId = $("#masterId").val();
            if ( masterId == "" ) {
                layer.msg("雇主ID不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

                });
                return;
            }
            var name = $("#name").val();
            if ( name == "" ) {
                layer.msg("姓名不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

                });
                return;
            }

            var loadingIndex = null;
            $.ajax({
                type : "POST",
                url  : "${APP_PATH}/user/update",
                data : {
                    "hunterId" : hunterId,
                    "masterId" : masterId,
                    "name" : name,
                    "sex"  : $("#sex").val(),
                    "phone" : $("#phone").val(),
                    "email" : $("#email").val(),
                },
                beforeSend : function() {
                    loadingIndex = layer.msg('处理中', {icon: 16});
                },
                success : function(data) {
                    layer.close(loadingIndex);
                    if ( data.status==200 ) {
                        layer.msg("用户信息修改成功", {time:1000, icon:6}, function(){
                            window.location.href = "${APP_PATH}/user/index";
                        });
                    } else {
                        layer.msg(data.msg, {time:2000, icon:5, shift:6}, function(){

                        });
                    }
                }
            });
        });
    });
</script>
</body>
</html>
