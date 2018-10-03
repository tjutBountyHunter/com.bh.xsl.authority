package com.xsl.authority.controller;

import com.xsl.authority.pojo.XslGroupRule;
import com.xsl.authority.service.GroupService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限组管理Controller
 */
@Controller
@RequestMapping("/group")
public class GroupRuleController {

    @Resource
    private GroupService groupService;
    /**
     * 展示权限组管理首页
     * @return
     */
    @RequestMapping("/index")
    public String showRulePage(){
        return "grouprule/index";
    }

    /**
     * 展示数据
     * @return
     */
    @RequestMapping("/loaddata")
    @ResponseBody
    public Object loadData(){
        List<XslGroupRule> rules = new ArrayList<>();
        rules = groupService.loadData();
        return rules;
    }

    /**
     * 跳转添加权限页面
     * @return
     */
    @RequestMapping("/add")
    public String showAddRule(){
        return "grouprule/add";
    }

    /**
     * 添加权限
     * @param xslGroupRule
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public XslResult addRules(XslGroupRule xslGroupRule){
        XslResult result = null;
        try {
            result = groupService.insertGroupRules(xslGroupRule);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"权限组信息添加失败");
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
        XslGroupRule rule = groupService.getGroupRuleById(id);
        model.addAttribute("rule",rule);
        return "grouprule/edit";
    }

    /**
     * 修改权限组信息
     * @param xslGroupRule
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public XslResult updateRule(XslGroupRule xslGroupRule){
        XslResult result = null;
        try {
            result = groupService.updateGroupRule(xslGroupRule);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"权限组信息修改失败");
        }
    }

    /**
     * 通过id删除权限组信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public XslResult deleteRule(Integer id){
        XslResult result = null;
        try {
            result = groupService.deleteGroupRuleById(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"权限组信息删除失败");
        }
    }
}
