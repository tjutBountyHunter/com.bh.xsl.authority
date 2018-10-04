package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslLevelRule;

import java.util.List;

public interface AuthorizateMapper {
    /**
     *  通过id来获取管理员权限
     * @param id
     * @return
     */
    List<XslLevelRule> getAdminRulesById(Integer id);

    /**
     * 通过id来获取猎人权限
     * @param hunterId
     * @return
     */
    List<XslLevelRule> getHunterRulesById(Integer hunterId);

    /**
     * 通过id来或获取雇主权限
     * @param masterId
     * @return
     */
    List<XslLevelRule> getMasterRulesById(Integer masterId);

    /**
     *  通过id来获取管理员权限
     * @param roleId
     * @return
     */
    List<XslLevelRule> getManagerRulesById(Integer roleId);
}
