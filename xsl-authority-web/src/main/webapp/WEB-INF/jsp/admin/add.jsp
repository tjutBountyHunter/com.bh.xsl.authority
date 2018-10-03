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
    <%@include file="/WEB-INF/jsp/common/head.jsp"%>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="tree">
                <%@include file="/WEB-INF/jsp/common/menu.jsp"%>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div id="userform" class="panel panel-default">
                <div class="panel-heading">完善信息<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form role="form">
                        <div class="form-group">
                            <label for="name">管理员名</label>
                            <input type="text" class="form-control" id="name" placeholder="请输入管理员名">
                        </div>
                        <div class="form-group">
                            <label for="sex">性别</label>
                            <input type="text" class="form-control" id="sex" placeholder="请输入性别">
                            <p class="help-block label label-warning">请输入合法的性别,只允许男或女</p>
                        </div>
                        <div class="form-group">
                            <label for="phone">电话</label>
                            <input type="text" class="form-control" id="phone" placeholder="请输入电话">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱</label>
                            <input type="text" class="form-control" id="email" placeholder="请输入邮箱">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="button" id="insertBtn" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
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

        $("#insertBtn").click(function(){
            var adminname = $("#name").val();
            if ( adminname == "" ) {
                layer.msg("管理员名不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

                });
                return;
            }
            var sex = $("#sex").val();
            if ( sex == "" ) {
                layer.msg("性别不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

                });
                return;
            }
            var phone = $("#phone").val();
            if ( phone == "" ) {
                layer.msg("电话号码不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

                });
                return;
            }
            var email = $("#email").val();
            if ( email == "" ) {
                layer.msg("邮箱不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

                });
                return;
            }
            var loadingIndex = null;
            $.ajax({
                type : "POST",
                url  : "${APP_PATH}/admin/insert",
                data : {
                    "name" : adminname,
                    "sex"  : sex,
                    "phone":phone,
                    "email" : email
                },
                beforeSend : function() {
                    loadingIndex = layer.msg('处理中', {icon: 16});
                },
                success : function(data) {
                    layer.close(loadingIndex);
                    if ( data.status==200 ) {
                        layer.msg("管理员信息保存成功", {time:1000, icon:6}, function(){
                            window.location.href = "${APP_PATH}/admin/index";
                        });
                    } else {
                        layer.msg("管理员信息添加失败，请重新操作", {time:2000, icon:5, shift:6}, function(){

                        });
                    }
                }
            });
        });
    });
</script>
</body>
</html>
