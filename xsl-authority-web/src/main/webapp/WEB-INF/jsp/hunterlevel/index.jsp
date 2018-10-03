<%--
  Created by IntelliJ IDEA.
  User: ssjs
  Date: 2018/8/1
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="descrription" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
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
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" onclick="deleteHunterLevels()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/hunterlevel/add'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <form id="hunterlevelForm">
                        <table class="table  table-bordered">
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox" id="allSelectBox"></th>
                                <th>id</th>
                                <th>猎人等级</th>
                                <th>猎人等级名称</th>
                                <th>猎人等级描述</th>
                                <th>创建时间</th>
                                <th>更新时间</th>
                                <th width="125">操作</th>
                            </tr>
                            </thead>
                            <tbody id="hunterData">

                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="9" align="center">
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

            $("#hunterData :checkbox").each(function(){
                this.checked = flg;
            });
        });
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
            url : "${APP_PATH}/hunterlevel/pageQuery",
            data : jsonData,
            beforeSend : function () {
                loadingIndex = layer.msg("处理中",{icon:16});
            },
            success :function (result) {
                layer.close(loadingIndex);
                if(result.status == 200){
                    //局部刷新页面数据
                    var tableContent = "";
                    var pageContent = ""

                    var hunterPage = result.data;
                    var hunterlevels = hunterPage.datas;
                    $.each(hunterlevels, function(i, hunterlevel){
                        tableContent +='<tr>';
                        tableContent +='    <td>'+(i+1)+'</td>';
                        tableContent +='    <td><input type="checkbox" name="hunterlevelid" value="'+hunterlevel.id+'"></td>';
                        tableContent +='    <td>'+hunterlevel.id+'</td>';
                        tableContent +='    <td>'+hunterlevel.level+'</td>';
                        tableContent +='    <td>'+hunterlevel.name+'</td>';
                        tableContent +='    <td>'+hunterlevel.descr+'</td>';
                        tableContent +='    <td>'+hunterlevel.createdate+'</td>';
                        tableContent +='    <td>'+hunterlevel.updatedate+'</td>';
                        tableContent +='    <td>';
                        tableContent +='        <button type="button" onclick="goPrivilegePage('+hunterlevel.id+')" class="btn btn btn-info btn-xs"><i class=" glyphicon glyphicon-pawn"></i></button>';
                        tableContent +='        <button type="button" onclick="goAssignPage('+hunterlevel.id+')" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                        tableContent +='       <button type="button" onclick="updateHunterLevel('+hunterlevel.id+')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                        tableContent +='        <button type="button" onclick="deleteHunterLevel('+hunterlevel.id+',\''+hunterlevel.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                        tableContent +='    </td>';
                        tableContent +='</tr>';
                    });

                    if ( pageno > 1 ) {
                        pageContent += '<li><a href="#" onclick="pageQuery('+(pageno-1)+')">上一页</a></li>';
                    }

                    for ( var i = 1; i <= hunterPage.totalno; i++ ) {
                        if ( i == pageno  ) {
                            pageContent += '<li class="active"><a  href="#">'+i+'</a></li>';
                        } else {
                            pageContent += '<li ><a href="#" onclick="pageQuery('+i+')">'+i+'</a></li>';
                        }
                    }

                    if ( pageno < hunterPage.totalno ) {
                        pageContent += '<li><a href="#" onclick="pageQuery('+(pageno+1)+')">下一页</a></li>';
                    }
                    $("#hunterData").html(tableContent);
                    $(".pagination").html(pageContent);

                }else{
                    layer.msg("猎人等级信息分页查询失败",{time:2000,icon:5,shift:6},function () {

                    });
                }
            }
        });
    }

    function updateHunterLevel(id) {
        window.location.href = "${APP_PATH}/hunterlevel/edit?id="+id;
    }

    function deleteHunterLevel( id, name ) {
        layer.confirm("删除猎人等级名为【"+name+"】, 是否继续",  {icon: 3, title:'提示'}, function(cindex){

            // 删除用户信息
            $.ajax({
                type : "POST",
                url  : "${APP_PATH}/hunterlevel/delete",
                data : { id : id },

                success : function(data) {
                    if ( data.status==200 ) {
                        pageQuery(1);
                    } else {
                        layer.msg("猎人等级信息删除失败", {time:2000, icon:5, shift:6}, function(){

                        });
                    }
                }
            });
            layer.close(cindex);

        }, function(cindex){
            layer.close(cindex);
        });
    }
    function deleteHunterLevels() {
        var boxes = $("#hunterData :checkbox");
        console.log(boxes.length);
        if ( boxes.length == 0 ) {
            layer.msg("请选择需要删除的猎人等级信息", {time:2000, icon:5, shift:6}, function(){

            });
        } else {
            layer.confirm("删除选择的猎人等级信息, 是否继续",  {icon: 3, title:'提示'}, function(cindex){
                // 删除选择的用户信息
                $.ajax({
                    type : "POST",
                    url  : "${APP_PATH}/hunterlevel/deletes",
                    data : $("#hunterlevelForm").serialize(),
                    success : function(data) {
                        if ( data.status==200 ) {
                            layer.msg("删除成功", {time:2000, icon:6}, function(){
                                pageQuery(1);
                            });
                        } else {
                            layer.msg("猎人等级信息删除失败", {time:2000, icon:5, shift:6}, function(){

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

    function goAssignPage(id) {
        window.location.href = "${APP_PATH}/hunterlevel/assgin?id="+id;
    }
    function goPrivilegePage(id) {
        window.location.href = "${APP_PATH}/hunterlevel/privilege?id="+id;
    }
</script>
</body>
</html>

</body>
</html>
