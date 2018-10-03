package com.xsl.authority.controller;

import com.xsl.authority.pojo.XslPrivilegeRule;
import com.xsl.authority.service.PrivilegeService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 特权管理Controller层
 */
@Controller
@RequestMapping("/privilege")
public class PrivilegeRuleController {

    @Resource
    private PrivilegeService privilegeService;
    /**
     * 展示权限管理首页
     * @return
     */
    @RequestMapping("/index")
    public String showRulePage(){
        return "privilegerule/index";
    }

    /**
     * 展示数据
     * @return
     */
    @RequestMapping("/loaddata")
    @ResponseBody
    public Object loadData(){
        List<XslPrivilegeRule> rules = new ArrayList<>();
        rules = privilegeService.loadData();
        return rules;
    }
    /**
     * 回显猎人特权已选权限
     * @return
     */
    @RequestMapping("/loadPrivilegeData")
    @ResponseBody
    public Object loadAssignData(Integer hunterlevelid){
        List<XslPrivilegeRule> rules = new ArrayList<>();
        rules = privilegeService.loadPrivilegeData(hunterlevelid);
        return rules;
    }

    /**
     * 回显猎人特权已选权限
     * @return
     */
    @RequestMapping("/loadMasterPrivilegeData")
    @ResponseBody
    public Object loadMasterAssignData(Integer masterlevelid){
        List<XslPrivilegeRule> rules = new ArrayList<>();
        rules = privilegeService.loadMasterPrivilegeData(masterlevelid);
        return rules;
    }
    /**
     * 跳转添加特权页面
     * @return
     */
    @RequestMapping("/add")
    public String showAddRule(){
        return "privilegerule/add";
    }

    /**
     * 添加特权
     * @param xslPrivilegeRule
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public XslResult addRules(XslPrivilegeRule xslPrivilegeRule){
        XslResult result = null;
        try {
            result = privilegeService.insertPrivilegeRules(xslPrivilegeRule);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"特权信息添加失败");
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
        XslPrivilegeRule privilege = privilegeService.getPrivilegeRuleById(id);
        model.addAttribute("privilege",privilege);
        return "privilegerule/edit";
    }

    /**
     * 修改权限信息
     * @param xslPrivilegeRule
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public XslResult updateRule(XslPrivilegeRule xslPrivilegeRule){
        XslResult result = null;
        try {
            result = privilegeService.updatePrivilegeRule(xslPrivilegeRule);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"特权信息修改失败");
        }
    }

    /**
     * 通过id删除特权信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public XslResult deleteRule(Integer id){
        XslResult result = null;
        try {
            result = privilegeService.deletePrivilegeRuleById(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"特权信息删除失败");
        }
    }
}
