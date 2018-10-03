package com.xsl.authority.service;

import com.xsl.authority.pojo.XslPrivilegeRule;
import com.xsl.authority.utils.XslResult;

import java.util.List;

public interface PrivilegeService {
    //加载特权数据
    List<XslPrivilegeRule> loadData();
    //添加权特权信息
    XslResult insertPrivilegeRules(XslPrivilegeRule xslPrivilegeRule);
    //通过id得到特权
    XslPrivilegeRule getPrivilegeRuleById(Integer id);
    //修改特权信息
    XslResult updatePrivilegeRule(XslPrivilegeRule xslPrivilegeRule);
    //通过id删除特权信息
    XslResult deletePrivilegeRuleById(Integer id);

    //回显猎人等级特权信息数据
    List<XslPrivilegeRule> loadPrivilegeData(Integer hunterlevelid);
    //回显雇主等级特权信息数据
    List<XslPrivilegeRule> loadMasterPrivilegeData(Integer masterlevelid);
}
