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
div class="container-fluid">
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
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">修改信息<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form id="hunterExperienceForm"  role="form">
                        <div class="form-group">
                            <label for="hunterid">猎人等级ID</label>
                            <input type="text" class="form-control" id="hunterid" value="${hunterexperience.hunterlevelid}" readonly="readonly" placeholder="请输入猎人ID">
                        </div>

                        <div class="form-group">
                            <label for="hunterlevel">猎人等级</label>
                            <input type="text" class="form-control" id="hunterlevel" value="${hunterexperience.level}" readonly="readonly" placeholder="请输入猎人等级">
                        </div>

                        <div class="form-group">
                            <label for="experience">猎人等级经验值</label>
                            <input type="text" class="form-control" id="experience" value="${hunterexperience.experience}" placeholder="请输入猎人等级经验值">
                        </div>
                        <div class="form-group">
                            <label for="createdate">猎人等级经验创建时间</label>
                            <input type="text" class="form-control" id="createdate" value="${hunterexperience.createdate}" readonly="readonly" placeholder="请输入猎人等级经验创建时间">
                        </div>
                        <button type="button" id="updateBtn" class="btn btn-success"><i class="glyphicon glyphicon-pencil"></i> 修改</button>
                        <button type="button" id="resetBtn" type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
            var dom = $('#hunterExperienceForm')[0];
            dom.reset();
        });

        $("#updateBtn").click(function(id){

            var experience = $("#experience").val();
            if ( experience == "" ) {
                layer.msg("猎人等级经验值不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

                });
                return;
            }
            console.log(experience);
            console.log($("#hunterlevel").val());
            var loadingIndex = null;
            $.ajax({
                type : "POST",
                url  : "${APP_PATH}/hunterexperience/update",
                data : {
                    "id" : "${param.id}",
                    "hunterlevelid" : $("#hunterid").val(),
                    "level" : $("#hunterlevel").val(),
                    "experience"  : experience,
                    "createdate"  : $("#createdate").val(),
                },
                beforeSend : function() {
                    loadingIndex = layer.msg('处理中', {icon: 16});
                },
                success : function(data) {
                    layer.close(loadingIndex);
                    if ( data.status==200 ) {
                        layer.msg("猎人等级经验修改成功", {time:1000, icon:6}, function(){
                            window.location.href = "${APP_PATH}/hunterexperience/index";
                        });
                    } else {
                        layer.msg("猎人等级经验修改失败", {time:2000, icon:5, shift:6}, function(){

                        });
                    }
                }
            });
        });
    });
</script>
</body>
</html>
