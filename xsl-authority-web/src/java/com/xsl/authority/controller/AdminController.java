package com.xsl.authority.controller;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslAdmin;
import com.xsl.authority.service.AdminService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 管理员Controller层
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 展示管理员信息首页
     * @return
     */
    @RequestMapping("/index")
    public String showAdminPage(){
        return "admin/index";
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
            PageDataResult result = adminService.getAdminList(queryText,pageno,pagesize);
            return XslResult.build(200,"管理员信息分页查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"管理员信息分页信息查询失败");
        }
    }

    /**
     * 展示管理员添加页面
     * @return
     */
    @RequestMapping("/add")
    public String showAdminAddPage(){
        return "admin/add";
    }

    /**
     * 添加管理员
     * @param xslAdmin
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public XslResult insertMasterLevel(XslAdmin xslAdmin){
        XslResult xslResult = null;
        try {
            xslResult = adminService.insertAdmin(xslAdmin);
            return  xslResult;
        }catch (Exception e){
            return XslResult.build(500,"管理员信息添加失败，请重新操作");
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
        XslAdmin admin = adminService.getAdminById(id);
        model.addAttribute("admin",admin);
        return "admin/edit";
    }

    /**
     * 修改管理员信息
     * @param xslAdmin
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public XslResult updateMasterLevel(XslAdmin xslAdmin){
        XslResult result = null;
        try {
            result =  adminService.updateAdmin(xslAdmin);
            return result;
        } catch ( Exception e ) {
            e.printStackTrace();
            return XslResult.build(500,"管理员信息修改失败，请重新操作");
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
            result = adminService.deleteAdminById(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"管理员信息删除失败");
        }
    }

    /**
     * 删除多个用户信息
     * @param adminid
     * @return
     */
    @RequestMapping("/deletes")
    @ResponseBody
    public XslResult deleteHunterLevels(Integer []adminid){
        XslResult result = null;
        try {
            result = adminService.deleteAdmins(adminid);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"管理员信息删除失败");
        }
    }

    /**
     * 跳转权限分配页面
     * @return
     */
    @RequestMapping("/adminassgin")
    public String showAssginPage(){
        return "admin/assgin";
    }

    /**
     * 为管理员分配权限
     * @param adminid
     * @param ruleids
     * @return
     */
    @RequestMapping("/doAssign")
    @ResponseBody
    public XslResult doAssgin(Integer adminid,Integer[] ruleids){
        XslResult xslResult = null;
        try {
            xslResult = adminService.inseAdminRule(adminid,ruleids);
            System.out.println(xslResult.getStatus());
            return xslResult;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"权限分配失败");
        }
    }
}
