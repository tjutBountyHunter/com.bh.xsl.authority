package com.xsl.authority.mapper;

import java.util.Map;

public interface doAssgin {

    //分配猎人权限信息
    void inserthunterlevelRule(Map<String,Object> paramMap);
    //删除某猎人等级所有权限
    void deleteHunterLevelRules(Map<String,Object> paramMap);

    //删除某雇主等级的所有权限
    void deleteMasterLevelRules(Map<String,Object> paramMap);
    //分配雇主权限信息
    void insertMasterlevelRule(Map<String,Object> paramMap);

    //删除某管理员的所有权限
    void deleteAdminRules(Map<String,Object> paramMap);
    //分配管理权限
    void insertAdminRule(Map<String,Object> paramMap);

    //删除猎人所分配的特权
    void deleteHunterLevelPrivilege(Map<String,Object> paramMap);
    //插入猎人所分配的特权
    void inserthunterlevelPrivilege(Map<String,Object> paramMap);
    //删除雇主所分配的特权
    void deleteMasterLevelPrivilege(Map<String,Object> paramMap);
    //分配某雇主特权
    void insertMasterlevelPrivilege(Map<String,Object> paramMap);
}
