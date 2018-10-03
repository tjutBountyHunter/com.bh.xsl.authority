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
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">Bounty Hunter-权限管理系统</a></div>
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
                            <li><a href="index.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
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
                        <span><i class="glyphicon glyphicon glyphicon-tadsdasd"></i> 权限管理 <span class="badge" style="float:right">3</span></span>
                        <ul style="margin-top:10px;">
                            <li style="height:40px;">
                                <a href="${APP_PATH}/user/index" style="color:red;"><i class="glyphicon glyphicon-user"></i> 用户维护</a>
                            </li>
                            <li style="height:40px;">
                                <a href="role.html"><i class="glyphicon glyphicon-king"></i> 角色维护</a>
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 用户信息</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" id="queryBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button"  class="btn btn-danger" style="float:right;margin-left:10px;" onclick="deleteUsers()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/user/add'"><i class="glyphicon glyphicon-plus"></i>新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <form id="userForm">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox" id="allSelectBox"></th>
                                <th>id</th>
                                <th>猎人id</th>
                                <th>雇主id</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>电话</th>
                                <th>邮箱地址</th>
                                <th>个性签名</th>
                                <th width="100">操作</th>
                            </tr>
                            </tr>
                            </thead>
                            <tbody id="userData">

                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="11" align="center">
                                    <ul class="pagination">
                                    </ul>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script src="${APP_PATH}/layer/layer.js"></script>
<script type="text/javascript">
    var likeflg = false;
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

        pageQuery(1);

        $("#queryBtn").click(function(){
            var queryText = $("#queryText").val();
            if ( queryText == "" ) {
                likeflg = false;
            } else {
                likeflg = true;
            }

            pageQuery(1);
        });

        $("#allSelectBox").click(function(){
            var flg = this.checked;
            console.log(flg);

            $("#userData :checkbox").each(function(){
                this.checked = flg;
            });
        });
    });
    $("tbody .btn-success").click(function(){
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function(){
        window.location.href = "edit.html";
    });
    //分页查询
    function pageQuery(pageno){
        var loadingIndex = null;

        var jsonData = {"pageno" : pageno, "pagesize" : 10};
        if ( likeflg == true ) {
            jsonData.queryText = $("#queryText").val();
        }
        $.ajax({
            type : "POST",
            url : "${APP_PATH}/user/pageQuery",
            data : jsonData,
            beforeSend : function () {
                loadingIndex = layer.msg("处理中",{icon:16});
            },
            success :function (result) {
                layer.close(loadingIndex);
                if(result.success){
                    //局部刷新页面数据
                    var tableContent = "";
                    var pageContent = ""

                    var userPage = result.data;
                    var users = userPage.datas;
                    $.each(users, function(i, user){
                        tableContent +='<tr>';
                        tableContent +='    <td>'+(i+1)+'</td>';
                        tableContent +='    <td><input type="checkbox" name="userid" value="'+user.id+'"></td>';
                        tableContent +='    <td>'+user.id+'</td>';
                        tableContent +='    <td>'+user.hunterId+'</td>';
                        tableContent +='    <td>'+user.masterId+'</td>';
                        tableContent +='    <td>'+user.name+'</td>';
                        tableContent +='    <td>'+user.sex+'</td>';
                        tableContent +='    <td>'+user.phone+'</td>';
                        tableContent +='    <td>'+user.email+'</td>';
                        tableContent +='    <td>'+user.signature+'</td>';
                        tableContent +='    <td>';
                        tableContent +='        <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                        tableContent +='        <button type="button" onclick="updateMessage('+user.id+')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                        tableContent +='        <button type="button" onclick="deleteUser('+user.id+',\''+user.hunterId+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                        tableContent +='    </td>';
                        tableContent +='</tr>';
                    });

                    if ( pageno > 1 ) {
                        pageContent += '<li><a href="#" onclick="pageQuery('+(pageno-1)+')">上一页</a></li>';
                    }

                    for ( var i = 1; i <= userPage.totalno; i++ ) {
                        if ( i == pageno  ) {
                            pageContent += '<li class="active"><a  href="#">'+i+'</a></li>';
                        } else {
                            pageContent += '<li ><a href="#" onclick="pageQuery('+i+')">'+i+'</a></li>';
                        }
                    }

                    if ( pageno < userPage.totalno ) {
                        pageContent += '<li><a href="#" onclick="pageQuery('+(pageno+1)+')">下一页</a></li>';
                    }
                    $("#userData").html(tableContent);
                    $(".pagination").html(pageContent);

                }else{
                    layer.msg("用户信息分页查询失败",{time:2000,icon:5,shift:6},function () {

                    });
                }
            }
        });
    }
    function updateMessage(id) {
        window.location.href = "${APP_PATH}/user/edit?id="+id;
    }
    function deleteUser( id, hunterId ) {
        layer.confirm("删除猎人ID为【"+hunterId+"】, 是否继续",  {icon: 3, title:'提示'}, function(cindex){

            // 删除用户信息
            $.ajax({
                type : "POST",
                url  : "${APP_PATH}/user/delete",
                data : { id : id },

                success : function(data) {
                    if ( data.status==200 ) {
                        pageQuery(1);
                    } else {
                        layer.msg(data.msg, {time:2000, icon:5, shift:6}, function(){

                        });
                    }
                }
            });

            layer.close(cindex);
        }, function(cindex){
            layer.close(cindex);
        });
    }
    function deleteUsers() {
        var boxes = $("#userData :checkbox");
        console.log(boxes.length);
        if ( boxes.length == 0 ) {
            layer.msg("请选择需要删除的用户信息", {time:2000, icon:5, shift:6}, function(){

            });
        } else {
            layer.confirm("删除选择的用户信息, 是否继续",  {icon: 3, title:'提示'}, function(cindex){
                // 删除选择的用户信息
                $.ajax({
                    type : "POST",
                    url  : "${APP_PATH}/user/deletes",
                    data : $("#userForm").serialize(),
                    success : function(data) {
                        if ( data.status==200 ) {
                            pageQuery(1);
                        } else {
                            layer.msg("用户信息删除失败", {time:2000, icon:5, shift:6}, function(){

                            });
                        }
                    }
                });

                layer.close(cindex);
            }, function(cindex){
                layer.close(cindex);
            });
        }
    }
</script>
</body>
</html>
