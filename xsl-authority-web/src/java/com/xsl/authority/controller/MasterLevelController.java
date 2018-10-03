package com.xsl.authority.controller;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslMasterLevel;
import com.xsl.authority.service.MasterLevelService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 雇主角色等级Controller层
 */
@Controller
@RequestMapping("/masterlevel")
public class MasterLevelController {

    @Resource
    private MasterLevelService masterLevelService;

    @RequestMapping("/index")
    public String showMasterLevelPage(){
        return "masterlevel/index";
    }

    /**
     * 分页查询展示雇主角色等级信息
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
            PageDataResult result = masterLevelService.getMasterLevelList(queryText,pageno,pagesize);
            return XslResult.build(200,"雇主角色等级信息分页查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"雇主角色等级信息分页信息查询失败");
        }
    }


    /**
     * 展示雇主角色等级添加首页
     * @return
     */
    @RequestMapping("/add")
    public String showAddPage(){
        return "masterlevel/add";
    }

    /**
     * 添加雇主角色信息
     * @param xslMasterLevel
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public XslResult insertMasterLevel(XslMasterLevel xslMasterLevel){
        XslResult xslResult = null;
        try {
            xslResult = masterLevelService.insertMasterLevel(xslMasterLevel);
            return  xslResult;
        }catch (Exception e){
            return XslResult.build(400,"雇主角色等级信息添加失败，请重新操作");
        }
    }

    /**
     * 显示所要修改的雇主等级信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String showEditPage(Integer id, Model model){
        XslMasterLevel masterLevel = masterLevelService.getMasterLevelById(id);
        model.addAttribute("masterlevel",masterLevel);
        return "masterlevel/edit";
    }

    /**
     * 跳转到权限分配管理
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/assgin")
    public String showAssginPage(Integer id, Model model){
        XslMasterLevel masterLevel = masterLevelService.getMasterLevelById(id);
        model.addAttribute("masterlevel",masterLevel);
        return "masterlevel/assgin";
    }

    /**
     * 修改雇主角色信息
     * @param xslMasterLevel
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public XslResult updateMasterLevel(XslMasterLevel xslMasterLevel){
        XslResult result = null;
        try {
            result =  masterLevelService.updaterMasterLevel(xslMasterLevel);
            return result;
        } catch ( Exception e ) {
            e.printStackTrace();
            return XslResult.build(400,"雇主角色等级信息修改失败，请重新操作");
        }
    }
    /**
     * 删除雇主等级角色信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public XslResult deleteUser(Integer id){
        XslResult result = null;
        try {
            result = masterLevelService.deleteMasterLeveById(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"用雇主角色信息删除失败");
        }
    }

    /**
     * 删除多个雇主信息
     * @param masterlevelid
     * @return
     */
    @RequestMapping("/deletes")
    @ResponseBody
    public XslResult deleteHunterLevels(Integer []masterlevelid){
        XslResult result = null;
        try {
            System.out.println(masterlevelid.length);
            result = masterLevelService.deleteMasterLevels(masterlevelid);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"雇主角色等级信息删除失败");
        }
    }


    /**
     * 跳转权限分配页面
     * @return
     */
    @RequestMapping("/masterassgin")
    public String shoAssginPage(){
        return "masterlevel/assgin";
    }
    /**
     *
     * @param masterlevelid
     * @param ruleids
     * @return
     */
    @RequestMapping("/doAssign")
    @ResponseBody
    public XslResult doAssgin(Integer masterlevelid,Integer[] ruleids){
        XslResult xslResult = null;
        try {
            xslResult = masterLevelService.inseMasterLeveRule(masterlevelid,ruleids);
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
    @RequestMapping("/masterprivilege")
    public String showPrivilegePage(){
        return "masterlevel/privilege";
    }

    /**
     * 分配特权
     * @param masterlevelid
     * @param privilegeids
     * @return
     */
    @RequestMapping("/doPrivilege")
    @ResponseBody
    public XslResult doPrivilege(Integer masterlevelid,Integer[] privilegeids){
        XslResult xslResult = null;
        try {
            xslResult = masterLevelService.inserMasterLevePrivilege(masterlevelid,privilegeids);
            return xslResult;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"雇主特权分配失败");
        }
    }
}
