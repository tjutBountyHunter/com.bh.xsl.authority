package com.xsl.authority.service;

import com.xsl.authority.pojo.XslLevelRule;
import com.xsl.authority.utils.XslResult;

import java.util.List;

public interface LevelRuleService {

    //加载权限数据
    List<XslLevelRule> loadData();

    //添加权限信息
    XslResult insertRules(XslLevelRule xslLevelRule);
    //通过id得到权限
    XslLevelRule getRuleById(Integer id);
    //修改权限信息
    XslResult updateRule(XslLevelRule xslLevelRule);
    //通过id删除权限信息
    XslResult deleteRuleById(Integer id);
    //回显猎人等级权限信息数据
    List<XslLevelRule> loadAssignData(Integer huterleveleid);
    //回显雇主等级权限信息数据
    List loadMasterAssignData(Integer masterlevelid);
    //回显管理员权限信息数据
    List<XslLevelRule> loadAdminAssignData(Integer adminid);
}
