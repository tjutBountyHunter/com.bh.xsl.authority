package com.xsl.authority.service;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslHunterLevel;
import com.xsl.authority.utils.XslResult;

public interface HunterLevelService {


    //分页显示猎人等级角色信息
    PageDataResult getHunterLevelList(String queryText, Integer pageno, Integer pagesize);

    //新增猎人等级角色信息
    XslResult insertHunterLevel(XslHunterLevel xslHunterLevel);

    //修改猎人角色等级信息
    XslResult updateHunterLevel(XslHunterLevel xslHunterLevel);

    //通过id来查找猎人角色角色等级信息
    XslHunterLevel getHunterLevelById(Integer id);

    //通过id删除猎人等级角色信息
    XslResult deleteHunterLeveById(Integer id);

    //通过id组来多选删除
    XslResult deleteHunterLevels(Integer []hunterlevelid);
    //分配权限信息
    XslResult inserHunterLeveRule(Integer hunterlevelid, Integer[] ruleids);
    //分配特权信息
    XslResult inserHunterLevePrivilege(Integer hunterlevelid, Integer[] ruleids);
}
