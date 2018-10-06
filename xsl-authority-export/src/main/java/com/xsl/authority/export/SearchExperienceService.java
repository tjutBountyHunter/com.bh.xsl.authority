package com.xsl.authority.export;

import com.xsl.authority.utils.XslResult;

/**
 * @author 梁俊伟
 * 提供经验查询接口
 */
public interface SearchExperienceService {

    /**
     * 获取猎人等级信息
     * @param level
     * @return
     */
    XslResult getHunterExperience(Integer level);

    /**
     * 获取雇主等级的经验值
     * @param level
     * @return
     */
    XslResult getMatserExperience(Integer level);

}
