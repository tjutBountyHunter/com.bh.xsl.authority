package com.xsl.authority.service;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslMasterLevel;
import com.xsl.authority.utils.XslResult;

public interface MasterLevelService {
    //分页显示雇主等级角色信息
    PageDataResult getMasterLevelList(String queryText, Integer pageno, Integer pagesize);

    //新增雇主等级角色信息
    XslResult insertMasterLevel(XslMasterLevel xslMasterLevel);

    //修改雇主角色等级信息
    XslResult updaterMasterLevel(XslMasterLevel xslMasterLevel);

    //通过id来查找雇主角色等级信息
    XslMasterLevel getMasterLevelById(Integer id);

    //通过id删除雇主等级角色信息
    XslResult deleteMasterLeveById(Integer id);

    //通过id组来多选删除
    XslResult deleteMasterLevels(Integer []masterlevelid);
    //分配雇主权限信息
    XslResult inseMasterLeveRule(Integer masterlevelid, Integer[] ruleids);

    //分配雇主特权限信息
    XslResult inserMasterLevePrivilege(Integer masterlevelid, Integer[] privilegeids);
}
