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
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 用户角色数目监控</h3>
                </div>
                <div class="panel-body">
                    <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;margin-left: 50px;margin-top: 20px">
                        <b>用户角色数目</b>
                    </blockquote>
                    <div class="col-sm-9 col-md-9 column" style="margin-left: 200px;margin-top: 20px">

                        <div id="main" style="width: 600px;height:400px;"></div>
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
<script src="${APP_PATH}/script/echarts.js"></script>
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
        $('#myTab a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        })
        $('#myTab1 a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        })

    });
    function loadData(option) {
        $.ajax({
            type : "POST",
            url : "${APP_PATH}/monitor/loaduserolecount",
            data : {

            },
            beforeSend : function () {
                loadingIndex = layer.msg("处理中",{icon:16});
            },
            success : function(result){
                layer.close(loadingIndex);
                if (result.status == 200){
                    var rulemonitor = result.data;
                    var ruledates = rulemonitor.categories;
                    var rulecount = rulemonitor.data;
                    option.xAxis.data = [];
                    option.series[0].data = [];
                    console.log(option.xAxis.data)
                    for (var i = 0; i < ruledates.length; i++) {
                        console.log(ruledates[i] + "---" + rulecount[i]);
                        option.xAxis.data.push(ruledates[i]);
                        option.series[0].data.push({
                            name:ruledates[i],
                            value: rulecount[i]
                        });
                    }
                    myChart.setOption(option);
                }
            }
        })
    }
    // 使用刚指定的配置项和数据显示图表。
    var myChart = echarts.init(document.getElementById('main'));

    var option = {
        color: ['#739fdb'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : [],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                min: 0,
                max: 10
            }
        ],
        series : [
            {
                name:'用户角色数目',
                type:'bar',
                barWidth: '40%',
                data:[]
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    loadData(option)
</script>
</body>
</html>
