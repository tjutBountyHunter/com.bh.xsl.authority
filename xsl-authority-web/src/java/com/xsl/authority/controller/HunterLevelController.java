package com.xsl.authority.controller;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslHunterLevel;
import com.xsl.authority.service.HunterLevelService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 猎人角色等级信息管理Controller层
 */
@Controller
@RequestMapping("hunterlevel")
public class HunterLevelController {

    @Resource
    private HunterLevelService hunterLevelService;

    @RequestMapping("/index")
    public String showMasterLevelPage(){
        return "hunterlevel/index";
    }

    /**
     * 分页查询展示猎人角色等级信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @RequestMapping("/pageQuery")
    @ResponseBody
    public XslResult pageQuery(String queryText, Integer pageno, Integer pagesize){
        XslResult xslResult = new XslResult();
        try {
            PageDataResult result = hunterLevelService.getHunterLevelList(queryText,pageno,pagesize);
            return XslResult.build(200,"雇主角色等级信息分页查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"雇主角色等级信息分页信息查询失败");
        }
    }

    /**
     * 展示猎人角色等级添加首页
     * @return
     */
    @RequestMapping("/add")
    public String showAddPage(){
        return "hunterlevel/add";
    }

    /**
     * 添加猎人角色信息
     * @param xslHunterLevel
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public XslResult insertMasterLevel(XslHunterLevel xslHunterLevel){
        XslResult xslResult = null;
        try {
            xslResult = hunterLevelService.insertHunterLevel(xslHunterLevel);
            return  xslResult;
        }catch (Exception e){
            return XslResult.build(400,"猎人角色等级信息添加失败，请重新操作");
        }
    }

    /**
     * 显示所要修改的猎人等级信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String showEditPage(Integer id, Model model){
        XslHunterLevel hunterLevel = hunterLevelService.getHunterLevelById(id);
        model.addAttribute("hunterlevel",hunterLevel);
        return "hunterlevel/edit";
    }

    /**
     * 修改猎人角色角色信息
     * @param xslHunterLevel
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public XslResult updateMasterLevel(XslHunterLevel xslHunterLevel){
        XslResult result = null;
        try {
            result =  hunterLevelService.updateHunterLevel(xslHunterLevel);
            System.out.println(xslHunterLevel.getId());
            return result;
        } catch ( Exception e ) {
            e.printStackTrace();
            return XslResult.build(400,"猎人角色等级信息修改失败，请重新操作");
        }
    }

    /**
     * 删除猎人等级角色信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public XslResult deleteUser(Integer id){
        XslResult result = null;
        try {
            result = hunterLevelService.deleteHunterLeveById(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"用雇主角色信息删除失败");
        }
    }

    /**
     * 删除多个猎人信息
     * @param hunterlevelid
     * @return
     */
    @RequestMapping("/deletes")
    @ResponseBody
    public XslResult deleteHunterLevels(Integer []hunterlevelid){
        XslResult result = null;
        try {
            System.out.println(hunterlevelid.length);
            result = hunterLevelService.deleteHunterLevels(hunterlevelid);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"猎人角色等级信息删除失败");
        }
    }

    /**
     * 跳转权限分配页面
     * @return
     */
    @RequestMapping("/assgin")
    public String showAssginPage(){
        return "hunterlevel/assgin";
    }

    /**
     * 进行权限分配
     * @param hunterlevelid
     * @param ruleids
     * @return
     */
    @RequestMapping("/doAssign")
    @ResponseBody
    public XslResult doAssgin(Integer hunterlevelid,Integer[] ruleids){
        XslResult xslResult = null;
        try {
            xslResult = hunterLevelService.inserHunterLeveRule(hunterlevelid,ruleids);
            return xslResult;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"权限分配失败");
        }
    }

    /**
     * 显示分配特权页面
     * @return
     */
    @RequestMapping("/privilege")
    public String showPrivilegePage(){
        return "hunterlevel/privilege";
    }

    /**
     * 分配特权
     * @param hunterlevelid
     * @param privilegeids
     * @return
     */
    @RequestMapping("/doPrivilege")
    @ResponseBody
    public XslResult doPrivilege(Integer hunterlevelid,Integer[] privilegeids){
        XslResult xslResult = null;
        try {
            xslResult = hunterLevelService.inserHunterLevePrivilege(hunterlevelid,privilegeids);
            return xslResult;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"特权分配失败");
        }
    }
}
