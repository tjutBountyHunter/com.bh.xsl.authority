package com.xsl.authority.service;

import com.xsl.authority.pojo.XslLevelRule;
import com.xsl.authority.utils.XslResult;

import java.util.List;

public interface LevelRuleService {

    /**
     * 加载权限数据
     * @return
     */
    List<XslLevelRule> loadData();

    /**
     * 添加权限信息
     * @param xslLevelRule
     * @return
     */
    XslResult insertRules(XslLevelRule xslLevelRule);

    /**
     * 通过id得到权限
     * @param id
     * @return
     */
    XslLevelRule getRuleById(Integer id);

    /**
     * 修改权限信息
     * @param xslLevelRule
     * @return
     */
    XslResult updateRule(XslLevelRule xslLevelRule);

    /**
     * 通过id删除权限信息
     * @param id
     * @return
     */
    XslResult deleteRuleById(Integer id);

    /**
     * 回显猎人等级权限信息数据
     * @param huterleveleid
     * @return
     */
    List<XslLevelRule> loadAssignData(Integer huterleveleid);

    /**
     * 回显雇主等级权限信息数据
     * @param masterlevelid
     * @return
     */
    List loadMasterAssignData(Integer masterlevelid);

    /**
     * 回显管理员权限信息数据
     * @param adminid
     * @return
     */
    List<XslLevelRule> loadAdminAssignData(Integer adminid);

    /**
     * 回显管理员权限分配情况
     * @param roleid
     * @return
     */
    List<XslLevelRule> loadManagerAssignData(Integer roleid);
}
