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
    <link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">
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
                    <button class="btn btn-success" onclick="doAssign()">分配权限</button>
                    <ul id="ruleTree" class="ztree" style="margin-top: 10px"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script src="${APP_PATH}/layer/layer.js"></script>
<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
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
        console.log(${param.id});
        var setting = {
            check : {
                enable : true
            },
            async : {
                enable :true,
                url : "${APP_PATH}/levelrule/loadManagerAssignData?roleid=${param.id}",
                autoParam : ["id","name=n","level=lv"]
            },
            view: {
                selectedMulti: false,
                addDiyDom: function(treeId, treeNode){
                    var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
                    if ( treeNode.icon ) {
                        icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
                    }
                },
            }
        };
        
        $.fn.zTree.init($("#ruleTree"), setting);
    });
    function doAssign() {
        var treeObj = $.fn.zTree.getZTreeObj("ruleTree");
        var nodes = treeObj.getCheckedNodes(true);
        if ( nodes.length == 0 ) {
            layer.msg("请选择需要分配的权限", {time:2000, icon:5, shift:6}, function(){

            });
        } else {

            var s = "roleid=${param.id}";
            console.log(s);
            $.each(nodes, function(i, node){
                s += "&ruleids="+node.id
            });
            $.ajax({
                type : "POST",
                url  : "${APP_PATH}/managerrole/doAssign",
                data : s,
                success : function (data) {
                    console.log(data.status);
                    if ( data.status == 200 ) {
                        layer.msg("分配权限成功", {time:2000, icon:6}, function(){
                            window.location.href = "${APP_PATH}/managerrole/index";
                        });
                    } else {
                        layer.msg("分配权限失败", {time:2000, icon:5, shift:6}, function(){

                        });
                    }
                }
            });
        }
    }
</script>
</body>
</html>

</body>
</html>
