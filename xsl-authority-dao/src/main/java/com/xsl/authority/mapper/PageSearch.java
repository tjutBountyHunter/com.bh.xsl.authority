package com.xsl.authority.mapper;

import com.xsl.authority.pojo.*;

import java.util.List;
import java.util.Map;

public interface PageSearch {
    /**
     * 得到猎人等级信息列表
     * @param map
     * @return
     */
    List<XslHunterLevel> getHunterList(Map<String, Object> map);

    /**
     * 得到猎人角色等级信息条数
     * @param map
     * @return
     */
    int getHunterTotalCount(Map<String, Object> map);

    /**
     * 得到雇主等级信息列表
     * @param map
     * @return
     */
    List<XslMasterLevel> getMasterLevelList(Map<String, Object> map);

    /**
     * 得到雇主角色等级信息条数
     * @param map
     * @return
     */
    int getMasterLevelTotalCount(Map<String, Object> map);

    /**
     * 得到管理员信息列表
     * @param map
     * @return
     */
    List<XslAdmin> getAdminList(Map<String,Object> map);
    /**
     * 得到管理员人数
     * @param map
     * @return
     */
    int getAdminTotalCount(Map<String,Object> map);
    /**
     *得到猎人等级经验信息列表
     * @param map
     * @return
     */
    List<XslHunterLevelExperience> getHunterLevelExperience(Map<String,Object> map);

    /**
     * 得到猎人等级经验信息数
     * @param map
     * @return
     */
    int getHunterLevelExperienceTotalCount(Map<String,Object> map);

    /**
     *得到雇主等级经验信息列表
     * @param map
     * @return
     */
    List<XslMasterLevelExperience> getMasterExperience(Map<String,Object> map);
    /**
     * 得到雇主等级经验信息数
     * @param map
     * @return
     */
    int getMasterLevelExperienceTotalCount(Map<String,Object> map);

    /**
     * 得到管理员分页信息
     * @param map
     * @return
     */
    List<XslManager> getManagerList(Map<String,Object> map);

    /**
     * 得到管理员总数
     * @param map
     * @return
     */
    int getManagerTotalCount(Map<String,Object> map);

    /**
     * 得到管理员角色分页信息
     * @param map
     * @return
     */
    List<XslRole> getManagerRolesList(Map<String,Object> map);

    /**
     * 得到管理员总数
     * @param map
     * @return
     */
    int getManagerRoleTotalCount(Map<String,Object> map);
}
