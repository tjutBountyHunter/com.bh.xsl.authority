package com.xsl.authority.service;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslHunterLevelExperience;
import com.xsl.authority.utils.XslResult;

public interface HunterLevelExperienceService {
    //分页猎人等级经验信息
    PageDataResult getHunterExperience(String queryText, Integer pageno, Integer pagesize);

    //新增猎人等级经验信息
    XslResult insertHunterLevelExperience(XslHunterLevelExperience xslHunterLevelExperience);

    //修改猎人等级经验信息
    XslResult updaterHunterLevelExperience(XslHunterLevelExperience xslHunterLevelExperience);

    //通过id来查找猎人等级经验信息
    XslHunterLevelExperience getHunterLevelExperienceById(Integer id);

    //通过id删除猎人等级经验信息
    XslResult deleteHunterLevelExperienceById(Integer id);

    //通过id组来多选删除
    XslResult deleteHunterLevelExperience(Integer []hunterexperienceid);
}
