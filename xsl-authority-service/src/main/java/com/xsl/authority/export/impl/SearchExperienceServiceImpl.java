package com.xsl.authority.export.impl;

import com.xsl.authority.mapper.SearchExperienceMapper;
import com.xsl.authority.export.SearchExperienceService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchExperienceServiceImpl implements SearchExperienceService {

    @Autowired
    private SearchExperienceMapper searchExperienceMapper;

    @Override
    public XslResult getHunterExperience(Integer level) {
        Integer experience = searchExperienceMapper.getHunterExperience(level);
        System.out.println(experience);
        if (experience != null) {
            return XslResult.ok(experience);
        } else {
            return XslResult.build(500, "获取猎人等级经验值失败");
        }
    }

    @Override
    public XslResult getMatserExperience(Integer level) {
        Integer experience = searchExperienceMapper.getMasterExperience(level);
        if (experience != null) {
            return XslResult.ok(experience);
        } else {
            return XslResult.build(500, "获取雇主等级经验值失败");
        }
    }
}
