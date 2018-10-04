package com.xsl.authority.controller;

import com.xsl.authority.pojo.XslLevelRule;
import com.xsl.authority.service.LevelRuleService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理Controller
 */
@Controller
@RequestMapping("/levelrule")
public class LevelRuleController {

    @Resource
    private LevelRuleService levelRuleService;

    /**
     * 展示权限管理首页
     * @return
     */
    @RequestMapping("/index")
    public String showRulePage(){
        return "levelrule/index";
    }

    /**
     * 展示数据
     * @return
     */
    @RequestMapping("/loaddata")
    @ResponseBody 
    public Object loadData(){
        List<XslLevelRule> rules = new ArrayList<>();
        rules = levelRuleService.loadData();
        return rules;
    }

    /**
     * 回显已选权限
     * @return
     */
    @RequestMapping("/loadAssignData")
    @ResponseBody
    public Object loadAssignData(Integer hunterlevelid){
        List<XslLevelRule> rules = new ArrayList<>();
        rules = levelRuleService.loadAssignData(hunterlevelid);
        return rules;
    }

    /**
     * 回显雇主等级角色已选权限
     * @return
     */
    @RequestMapping("/loadMasterAssignData")
    @ResponseBody
    public Object loadMasterAssignData(Integer masterlevelid){
        List<XslLevelRule> rules = new ArrayList<>();
        rules = levelRuleService.loadMasterAssignData(masterlevelid);
        return rules;
    }

    /**
     * 回显管理员已选权限
     * @return
     */
    @RequestMapping("/loadAdminAssignData")
    @ResponseBody
    public Object loadAdminAssignData(Integer adminid){
        List<XslLevelRule> rules = new ArrayList<>();
        rules = levelRuleService.loadAdminAssignData(adminid);
        return rules;
    }

    /**
     * 回显管理员已选权限
     * @return
     */
    @RequestMapping("/loadManagerAssignData")
    @ResponseBody
    public Object loadManagerAssignData(Integer roleid){
        List<XslLevelRule> rules = new ArrayList<>();
        rules = levelRuleService.loadManagerAssignData(roleid);
        return rules;
    }

    /**
     * 跳转添加权限页面
     * @return
     */
    @RequestMapping("/add")
    public String showAddRule(){
        return "levelrule/add";
    }

    /**
     * 添加权限
     * @param xslLevelRule
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public XslResult addRules(XslLevelRule xslLevelRule){
        XslResult result = null;
        try {
            result = levelRuleService.insertRules(xslLevelRule);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"权限信息添加失败");
        }
    }

    /**
     * 显示修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String showEditRule(Integer id, Model model){
        XslLevelRule rule = levelRuleService.getRuleById(id);
        model.addAttribute("rule",rule);
        return "levelrule/edit";
    }

    /**
     * 修改权限信息
     * @param xslLevelRule
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public XslResult updateRule(XslLevelRule xslLevelRule){
        XslResult result = null;
        try {
            result = levelRuleService.updateRule(xslLevelRule);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"权限信息修改失败");
        }
    }

    /**
     * 通过id删除权限信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public XslResult deleteRule(Integer id){
        XslResult result = null;
        try {
            result = levelRuleService.deleteRuleById(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"权限信息删除失败");
        }
    }
}
