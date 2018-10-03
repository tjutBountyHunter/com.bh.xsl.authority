package com.xsl.authority.controller;

import com.xsl.authority.export.SearchLevelService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 查询等级信息
 */
@Controller
@RequestMapping("/level")
public class SearchLevelController {


    @Resource
    private SearchLevelService searchLevelService;
    /**
     * 返回猎人等级信息
     * @param level
     * @return
     */
    @RequestMapping("/hunter")
    @ResponseBody
    public XslResult getHunterLevelInfo(Integer level){
        XslResult xslResult = searchLevelService.getHunterLevelInfo(level);
        return xslResult;
    }

    /**
     * 返回雇主等级信息
     * @param level
     * @return
     */
    @RequestMapping("/master")
    @ResponseBody
    public XslResult getMasterLevelInfo(Integer level){
        XslResult xslResult = searchLevelService.getMasterLevelInfo(level);
        return xslResult;
    }

}
