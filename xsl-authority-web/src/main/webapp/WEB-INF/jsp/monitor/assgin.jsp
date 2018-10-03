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
                    <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;margin-left: 50px;margin-top: 20px">
                        <b> 权限调用情况</b>
                    </blockquote>
                    <div class="col-sm-9 col-md-9 column" style="margin-left: 150px;margin-top: 20px">

                        <div id="main" style="width: 650px;height:400px;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/script/docs.min.js"></script>
<script src="${APP_PATH}/script/echarts.js"></script>
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
    });
    function loadData(option) {
        $.ajax({
            type : "POST",
            url : "${APP_PATH}/monitor/monitorassgin",

            beforeSend : function () {
                loadingIndex = layer.msg("处理中",{icon:16});
            },
            success : function(result){
                layer.close(loadingIndex);
                if (result.status == 200){
                    var rulemonitor = result.data;
                    var rulenames = rulemonitor.categories;
                    var rulecount = rulemonitor.data;
                    option.legend.data = [];
                    option.series[0].data = [];
                    for (var i = 0; i < rulenames.length; i++) {
                        console.log(rulenames + "---" + rulecount);
                        option.legend.data.push(rulenames[i]);
                        option.series[0].data.push({
                            name: rulenames[i],
                            value: rulecount[i]
                        });
                    }
                    myChart.setOption(option);
                }
            }
        })
    }

    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title : {
            text: '权限分配情况表',
            x:'60%'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: []
        },
        series : [
            {
                name: '权限调用',
                type: 'pie',
                radius : '55%',
                center: ['70%', '60%'],
                data:[
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    loadData(option)
</script>
</body>
</html>
