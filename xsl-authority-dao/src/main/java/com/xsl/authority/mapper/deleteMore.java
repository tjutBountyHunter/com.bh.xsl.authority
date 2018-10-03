package com.xsl.authority.mapper;

import java.util.Map;

public interface deleteMore {

     //删除多个雇主等级信息
    void deleteMasterLevel(Map<String,Object> map);

    //删除多个猎人信息
    void deleteHunterLevel(Map<String,Object> map);
    //删除多个管理员信息
    void deleteAdmins(Map<String,Object> map);
    //删除多个猎人等级经验信息
    void deleteHunterExperience(Map<String,Object> map);
    //删除多个雇主等级经验信息
    void deleteMasterExperience(Map<String,Object> map);
}
