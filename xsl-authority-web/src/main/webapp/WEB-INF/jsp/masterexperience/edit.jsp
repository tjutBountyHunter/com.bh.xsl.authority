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
                    <form id="masterexperienceForm" role="form">
                        <div class="form-group">
                            <label for="masterid">雇主等级ID</label>
                            <input type="text" class="form-control" id="masterid" value="${masterexperience.masterlevelid}" readonly="readonly" placeholder="请输入雇主等级ID">
                        </div>
                        <div class="form-group">
                            <label for="masterlevel">雇主等级</label>
                            <input type="text" class="form-control" id="masterlevel" value="${masterexperience.level}" readonly="readonly" placeholder="请输入雇主等级">
                        </div>
                        <div class="form-group">
                        <label for="experiences">等级经验</label>
                        <input type="text" class="form-control" id="experiences" value="${masterexperience.experience}" placeholder="请输入雇主等级经验">
                         </div>
                        <div class="form-group">
                            <label for="createdate">雇主等级经验创建时间</label>
                            <input type="text" class="form-control" id="createdate" value="${masterexperience.createdate}" placeholder="请输入雇主等级经验创建时间">
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
            var dom = $('#masterexperienceForm')[0];
            dom.reset();
        });

        $("#updateBtn").click(function(id){

            var experience = $("#experiences").val();
            if ( experience == "" ) {
                layer.msg("经验值不能为空，请输入", {time:2000, icon:5, shift:6}, function(){

                });
                return;
            }

            var loadingIndex = null;
            $.ajax({
                type : "POST",
                url  : "${APP_PATH}/masterexperience/update",
                data : {
                    "id" : "${param.id}",
                    "masterlevelid" : $("#masterid").val(),
                    "level" : $("#masterlevel").val(),
                    "experience"  : experience,
                    "createdate"  : $("#createdate").val(),
                },
                beforeSend : function() {
                    loadingIndex = layer.msg('处理中', {icon: 16});
                },
                success : function(data) {
                    layer.close(loadingIndex);
                    if ( data.status==200 ) {
                        layer.msg("雇主等级经验信息修改成功", {time:1000, icon:6}, function(){
                            window.location.href = "${APP_PATH}/masterexperience/index";
                        });
                    } else {
                        layer.msg("雇主等级经验信息修改失败", {time:2000, icon:5, shift:6}, function(){

                        });
                    }
                }
            });
        });
    });
</script>
</body>
</html>
