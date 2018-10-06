package com.xsl.authority.controller;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslMasterLevelExperience;
import com.xsl.authority.service.MasterLevelExperienceService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *雇主等级经验信息Controller层
 */
@Controller
@RequestMapping("masterexperience")
public class MasterLevelExperienceContorller {

    @Resource
    private MasterLevelExperienceService masterLevelExperienceService;

    /**
     * 展示雇主等级经验信息首页
     * @return
     */
    @RequestMapping("/index")
    public String showAdminPage(){
        return "masterexperience/index";
    }

    /**
     * 分页查询管理员信息
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
            PageDataResult result = masterLevelExperienceService.getMasterExperience(queryText,pageno,pagesize);
            return XslResult.build(200,"雇主等级经验信息分页查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"雇主等级经验信息分页信息查询失败");
        }
    }

    /**
     * 展示雇主等级经验信息添加页面
     * @return
     */
    @RequestMapping("/add")
    public String showAdminAddPage(){
        return "masterexperience/add";
    }

    /**
     * 添加雇主等级经验信息
     * @param xslMasterLevelExperience
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public XslResult insertMasterLevel(XslMasterLevelExperience xslMasterLevelExperience){
        XslResult xslResult = null;
        try {
            xslResult = masterLevelExperienceService.insertMasterLevelExperience(xslMasterLevelExperience);
            return  xslResult;
        }catch (Exception e){
            return XslResult.build(400,"雇主等级经验信息添加失败，请重新操作");
        }
    }

    /**
     * 显示所要修改的管理员信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String showEditPage(Integer id, Model model){
        XslMasterLevelExperience masterexperience = masterLevelExperienceService.getMasterLevelExperienceById(id);
        model.addAttribute("masterexperience",masterexperience);
        return "masterexperience/edit";
    }

    /**
     * 修改猎人等级经验信息
     * @param xslMasterLevelExperience
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public XslResult updateMasterLevel(XslMasterLevelExperience xslMasterLevelExperience){
        XslResult result = null;
        try {
            result =  masterLevelExperienceService.updaterMasterLevelExperience(xslMasterLevelExperience);
            return result;
        } catch ( Exception e ) {
            e.printStackTrace();
            return XslResult.build(400,"猎人等级经验信息修改失败，请重新操作");
        }
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public XslResult deleteUser(Integer id){
        XslResult result = null;
        try {
            result = masterLevelExperienceService.deleteMasterLevelExperienceById(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"猎人等级经验信息删除失败");
        }
    }

    /**
     * 删除多个雇主等级经验信息
     * @param hunterexperienceid
     * @return
     */
    @RequestMapping("/deletes")
    @ResponseBody
    public XslResult deleteHunterLevels(Integer []masterexperienceid){
        XslResult result = null;
        try {
            System.out.println(masterexperienceid.length);
            //删除多个猎人等级经验信息
            result = masterLevelExperienceService.deleteMasterLevelExperience(masterexperienceid);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"删除所选雇主等级经验信息删除失败");
        }
    }

}
