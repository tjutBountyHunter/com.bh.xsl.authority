<%@page pageEncoding="UTF-8"%>
<ul style="padding-left:0px;" class="list-group">
    <li class="list-group-item tree-closed">
        <span><i class="glyphicon glyphicon-king"></i> 角色管理 <span class="badge" style="float:right">5</span></span>
        <ul style="margin-top:10px;display:none;">
            <li style="height:40px">
                <a href="${APP_PATH}/hunterlevel/index"><i class= "glyphicon glyphicon-king"></i> 猎人等级管理</a>
            </li>
            <li style="height:40px;">
                <a href="${APP_PATH}/masterlevel/index"><i class="glyphicon glyphicon-king"></i> 雇主等级管理</a>
            </li>
            <li style="height:40px;">
                <a href="${APP_PATH}/admin/index"><i class="glyphicon glyphicon-king"></i> 管理员管理</a>
            </li>
            <li style="height:40px;">
                <a href="${APP_PATH}/hunterexperience/index"><i class="glyphicon glyphicon-star"></i>猎人经验值管理</a>
            </li>
            <li style="height:40px;">
                <a href="${APP_PATH}/masterexperience/index"><i class="glyphicon glyphicon-star"></i>雇主经验值管理</a>
            </li>
        </ul>
    </li>
    <li class="list-group-item tree-closed">
        <span><i class="glyphicon glyphicon-th-large"></i> 权限分配管理 <span class="badge" style="float:right">3</span></span>
        <ul style="margin-top:10px;display:none;">
            <li style="height:40px;">
                <a href="${APP_PATH}/levelrule/index"><i class="glyphicon glyphicon-picture"></i> 权限管理</a>
            </li>
            <li style="height:40px;">
                <a href="${APP_PATH}/group/index"><i class="glyphicon glyphicon-random"></i> 权限组管理</a>
            </li>
            <li style="height:40px;">
                <a href="${APP_PATH}/privilege/index" ><i class="glyphicon glyphicon-hdd"></i> 权限特权管理</a>
            </li>
        </ul>
    </li>
    <li class="list-group-item tree-closed">
        <span><i class="glyphicon glyphicon glyphicon-user"></i> 用户角色监控 <span class="badge" style="float:right">1</span></span>
        <ul style="margin-top:10px;display:none;">
            <li style="height:30px;">
                <a href="${APP_PATH}/monitor/userolecount"><i class="glyphicon glyphicon-adjust"></i>数目监控</a>
            </li>
        </ul>
    </li>
    <li class="list-group-item tree-closed">
        <span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限监控 <span class="badge" style="float:right">2</span></span>
        <ul style="margin-top:10px;display:none;">
            <li style="height:40px;">
                <a href="${APP_PATH}/monitor/use"><i class="glyphicon glyphicon-lock"></i> 权限调用监控</a>
            </li>
            <li style="height:40px;">
                <a href="${APP_PATH}/monitor/assgin"><i class="glyphicon glyphicon-lock"></i> 权限分配监控</a>
            </li>
        </ul>
    </li>
</ul>