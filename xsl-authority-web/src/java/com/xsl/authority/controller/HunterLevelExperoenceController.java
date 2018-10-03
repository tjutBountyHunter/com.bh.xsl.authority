package com.xsl.authority.controller;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslHunterLevelExperience;
import com.xsl.authority.service.HunterLevelExperienceService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 猎人等级经验管理
 */

@Controller
@RequestMapping("/hunterexperience")
public class HunterLevelExperoenceController {

    @Resource
    private HunterLevelExperienceService hunterLevelExperienceService;

    @RequestMapping("/index")
    public String showHunterExperiencePage(){
        return "hunterexperience/index";
    }

    /**
     * 猎人等级经验分页查询
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
            PageDataResult result = hunterLevelExperienceService.getHunterExperience(queryText,pageno,pagesize);
            return XslResult.build(200,"猎人等级经验页查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"猎人等级信息分页信息查询失败");
        }
    }

    /**
     * 展示猎人等级经验添加页面
     * @return
     */
    @RequestMapping("/add")
    public String showAddPage(){
        return "hunterexperience/add";
    }

    /**
     * 添加雇主角色信息
     * @param xslHunterLevelExperience
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public XslResult insertMasterLevel(XslHunterLevelExperience xslHunterLevelExperience){
        XslResult xslResult = null;
        try {
            //添加猎人等级经验
            System.out.print(xslHunterLevelExperience.getHunterlevelid());
            xslResult = hunterLevelExperienceService.insertHunterLevelExperience(xslHunterLevelExperience);
            return  xslResult;
        }catch (Exception e){
            return XslResult.build(400,"猎人等级经验信息添加失败，请重新操作");
        }
    }

    /**
     * 显示所要修改的猎人等级经验信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String showEditPage(Integer id, Model model){
        XslHunterLevelExperience hunterexperience = hunterLevelExperienceService.getHunterLevelExperienceById(id);
        model.addAttribute("hunterexperience",hunterexperience);
        return "hunterexperience/edit";
    }

    /**
     * 修改猎人等级经验信息
     * @param xslHunterLevelExperience
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public XslResult updateMasterLevel(XslHunterLevelExperience xslHunterLevelExperience){
        XslResult result = null;
        try {
            //修改信息
            result =  hunterLevelExperienceService.updaterHunterLevelExperience(xslHunterLevelExperience);
            return result;
        } catch ( Exception e ) {
            e.printStackTrace();
            return XslResult.build(400,"猎人等级经验信息修改失败，请重新操作");
        }
    }

    /**
     * 删除猎人等级经验信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public XslResult deleteUser(Integer id){
        XslResult result = null;
        try {
            //删除猎人等级经验信息
            result = hunterLevelExperienceService.deleteHunterLevelExperienceById(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"猎人等级经验信息删除失败");
        }
    }

    /**
     * 删除多个猎人等级经验信息
     * @param hunterexperienceid
     * @return
     */
    @RequestMapping("/deletes")
    @ResponseBody
    public XslResult deleteHunterLevels(Integer []hunterexperienceid){
        XslResult result = null;
        try {
            //删除多个猎人等级经验信息
            result = hunterLevelExperienceService.deleteHunterLevelExperience(hunterexperienceid);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"删除所选猎人等级经验信息删除失败");
        }
    }
}
