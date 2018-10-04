package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslAdmin;
import com.xsl.authority.pojo.XslManager;
import com.xsl.authority.pojo.XslRole;

import java.util.Map;

public interface UpdateMessage {

    /**
     * 更新管理员信息
     * @param xslAdmin
     */
    void updateAdmin(XslAdmin xslAdmin);

    /**
     * 更新状态来删除管理员信息
     * @param map
     */
    void updateState(Map map);

    /**
     * 更新管理员信息
     * @param xslManager
     */
    void updateManager(XslManager xslManager);
    /**
     * 更新状态来删除管理员信息
     * @param map
     */
    void updateManagerState(Map map);

    /**
     * 更新管理员角色信息
     * @param xslRole
     */
    void updateManagerRole(XslRole xslRole);

    /**
     * 更新状态来删除管理员角色信息
     * @param map
     */
    void deleteManagerRoleById(Map map);
}