package com.xsl.authority.service;

import com.xsl.authority.pojo.XslLevelRule;

import java.util.List;

public interface GetLevelRule {

    List<XslLevelRule> getHunterRule(Integer hunterId);

    List<XslLevelRule> getMatserRule(Integer masterId);

    List<XslLevelRule> getManagerRule(Integer managerid);
}
