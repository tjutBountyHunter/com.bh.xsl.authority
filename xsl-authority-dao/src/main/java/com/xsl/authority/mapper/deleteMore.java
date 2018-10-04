package com.xsl.authority.mapper;

import java.util.Map;

public interface deleteMore {

    /**
     * 删除多个雇主等级信息
     * @param map
     */
    void deleteMasterLevel(Map<String,Object> map);

    /**
     * 删除多个猎人信息
     * @param map
     */
    void deleteHunterLevel(Map<String,Object> map);

    /**
     * 删除多个管理员信息
     * @param map
     */
    void deleteAdmins(Map<String,Object> map);

    /**
     * 删除多个猎人等级经验信息
     * @param map
     */
    void deleteHunterExperience(Map<String,Object> map);

    /**
     * 删除多个雇主等级经验信息
     * @param map
     */
    void deleteMasterExperience(Map<String,Object> map);

    /**
     * 删除多个管理员信息
     */
    void deleteManagers(Map<String,Object> map);

    /**
     * 删除多个管理员角色信息
     * @param map
     */
    void deleteManagerRoles(Map<String,Object> map);
}
