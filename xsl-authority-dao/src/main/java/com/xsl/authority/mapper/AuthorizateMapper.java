package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslLevelRule;

import java.util.List;

public interface AuthorizateMapper {
    //通过id来获取管理员权限
    List<XslLevelRule> getAdminRulesById(Integer id);
    //通过id来获取猎人权限
    List<XslLevelRule> getHunterRulesById(Integer hunterId);
    //通过id来或获取雇主权限
    List<XslLevelRule> getMasterRulesById(Integer masterId);
}
