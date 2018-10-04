package com.xsl.authority.mapper;

import java.util.Map;

public interface doAssgin {

    /**
     * 分配猎人权限信息
     * @param paramMap
     */
    void inserthunterlevelRule(Map<String,Object> paramMap);

    /**
     * 删除某猎人等级所有权限
     * @param paramMap
     */
    void deleteHunterLevelRules(Map<String,Object> paramMap);

    /**
     * 删除某雇主等级的所有权限
     * @param paramMap
     */
    void deleteMasterLevelRules(Map<String,Object> paramMap);

    /**
     * 分配雇主权限信息
     * @param paramMap
     */
    void insertMasterlevelRule(Map<String,Object> paramMap);

    /**
     * /删除某管理员的所有权限
     * @param paramMap
     */
    void deleteAdminRules(Map<String,Object> paramMap);

    /**
     * 分配管理权限
     * @param paramMap
     */
    void insertAdminRule(Map<String,Object> paramMap);

    /**
     * 删除猎人所分配的特权
     * @param paramMap
     */
    void deleteHunterLevelPrivilege(Map<String,Object> paramMap);

    /**
     * 插入猎人所分配的特权
     * @param paramMap
     */
    void inserthunterlevelPrivilege(Map<String,Object> paramMap);

    /**
     * 删除雇主所分配的特权
     * @param paramMap
     */
    void deleteMasterLevelPrivilege(Map<String,Object> paramMap);

    /**
     * 分配某雇主特权
     * @param paramMap
     */
    void insertMasterlevelPrivilege(Map<String,Object> paramMap);

    /**
     * 删除管理员角色所分配的权限
     * @param paramMap
     */
    void deleteManagerRules(Map<String,Object> paramMap);

    /**
     * 分配某管理员角色权限
     * @param paramMap
     */
    void insertManagerRules(Map<String,Object> paramMap);
}
