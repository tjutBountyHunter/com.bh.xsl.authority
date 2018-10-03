package com.xsl.authority.controller;

import com.xsl.authority.export.SearchExperienceService;
import com.xsl.authority.pojo.XslLevelRule;
import com.xsl.authority.service.GetLevelRule;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试接口信息
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private SearchExperienceService searchExperienceService;

    @Resource
    private GetLevelRule getLevelRule;

    @RequestMapping("/hunter")
    @ResponseBody
    public XslResult getHunterExperience(Integer level){
        XslResult xslResult = searchExperienceService.getHunterExperience(level);
        return  xslResult;
    }

    @RequestMapping("/master")
    @ResponseBody
    public XslResult getMatserExperience(Integer level){
        XslResult xslResult = searchExperienceService.getMatserExperience(level);
        return  xslResult;
    }
    @RequestMapping("/hunterrule")
    @ResponseBody
    public XslResult getHunterlevel(Integer hunterId){
        List<XslLevelRule> hunterRule = getLevelRule.getHunterRule(hunterId);
        for(XslLevelRule xslLevelRule:hunterRule){
            System.out.println(xslLevelRule.getName());
        }
        try {
            return XslResult.ok(hunterRule);
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"猎人权限获取异常");
        }
    }
}
