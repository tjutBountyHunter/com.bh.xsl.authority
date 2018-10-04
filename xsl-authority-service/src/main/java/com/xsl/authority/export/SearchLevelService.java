package com.xsl.authority.export;

import com.xsl.authority.utils.XslResult;

/**
 * @author 梁俊伟
 * 提供等级查询的接口
 */
public interface SearchLevelService {

    /**
     * 查询猎人的等级信息
     * @param level
     * @return
     */
    XslResult getHunterLevelInfo(Integer level);

    /**
     * 查询猎人的等级信息
     * @param level
     * @return
     */
    XslResult getMasterLevelInfo(Integer level);
}
