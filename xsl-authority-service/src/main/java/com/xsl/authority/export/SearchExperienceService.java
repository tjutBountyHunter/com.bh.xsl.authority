package com.xsl.authority.export;

import com.xsl.authority.utils.XslResult;

public interface SearchExperienceService {

    //获取猎人等级的经验值
    XslResult getHunterExperience(Integer level);
    //获取雇主等级的经验值
    XslResult getMatserExperience(Integer level);

}
