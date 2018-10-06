<%@page pageEncoding="UTF-8"%>
<div class="container-fluid">
    <div class="navbar-header"><div><a class="navbar-brand" style="font-size:22px;" href="${APP_PATH}/">悬赏令权限管理系统</a></div>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
                <div class="btn-group">
                    <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
                        <i class="glyphicon glyphicon-user"></i> ${xslManager.managerName} <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li class="divider"></li>
                        <li><a href="${APP_PATH}/logout"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
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
            <input type="text" class="form-control" placeholder="查询">
        </form>
    </div>
</div>