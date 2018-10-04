package com.xsl.authority.controller;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslManager;
import com.xsl.authority.pojo.XslRole;
import com.xsl.authority.service.ManagerRoleService;
import com.xsl.authority.service.ManagerService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/managers")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @Resource
    private ManagerRoleService managerRoleService;


    /**
     * 展示管理员信息首页
     * @return
     */
    @RequestMapping("/index")
    public String showAdminPage(){
        return "manager/index";
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
            PageDataResult result = managerService.getManagerist(queryText,pageno,pagesize);
            return XslResult.build(200,"管理员信息分页查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"管理员信息分页信息查询失败");
        }
    }

    /**
     * 展示管理员添加页面
     * @return
     */
    @RequestMapping("/add")
    public String showAdminAddPage(){
        return "manager/add";
    }

    /**
     * 添加管理员
     * @param xslManager
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public XslResult insertMasterLevel(XslManager xslManager){
        XslResult xslResult = null;
        try {
            xslResult = managerService.insertManager(xslManager);
            return  xslResult;
        }catch (Exception e){
            return XslResult.build(400,"管理员信息添加失败，请重新操作");
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
        XslManager xslManager = managerService.getManagerById(id);
        model.addAttribute("admin",xslManager);
        return "manager/edit";
    }

    /**
     * 修改管理员信息
     * @param xslManager
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public XslResult updateMasterLevel(XslManager xslManager){
        XslResult result = null;
        try {
            result = managerService.updateManager(xslManager);
            return result;
        } catch ( Exception e ) {
            e.printStackTrace();
            return XslResult.build(400,"管理员信息修改失败，请重新操作");
        }
    }

    /**
     * 显示所要修改的管理员信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/assginrole")
    public String showAssginRolePage(Integer id, Model model){
        XslManager xslManager = managerService.getManagerById(id);
        model.addAttribute("admin",xslManager);
        List<XslRole> assginRoles = new ArrayList<>();
        List<XslRole> unassginRoles = new ArrayList<>();
        managerRoleService.loadAssginRoles(assginRoles,unassginRoles,id);
        model.addAttribute("assginRoles",assginRoles);
        model.addAttribute("unassginRoles",unassginRoles);
        return "manager/role";
    }
    /**
     * 进行角色分配
     * @return
     */
    @RequestMapping("/doRoleAssign")
    @ResponseBody
    public XslResult doRoleAssgin(Integer managerid, Integer[] unassignroleids){
        XslResult xslResult = null;
        try {
            xslResult = managerService.doRoleAssgin(managerid,unassignroleids);
            return xslResult;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"管理员角色信息分配失败");
        }
    }

    /**
     * 取消角色分配
     * @return
     */
    @RequestMapping("/dounRoleAssign")
    @ResponseBody
    public XslResult dounRoleAssgin(Integer managerid, Integer[] assginroleids){
        XslResult xslResult = null;
        try {
            xslResult = managerService.dounRoleAssgin(managerid,assginroleids);
            return xslResult;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"管理员角色信息取消失败");
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
            result = managerService.deleteManagerById(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"管理员信息删除失败");
        }
    }

    /**
     * 删除多个用户信息
     * @param managerid
     * @return
     */
    @RequestMapping("/deletes")
    @ResponseBody
    public XslResult deleteHunterLevels(Integer []managerid){
        XslResult result = null;
        try {
            result = managerService.deletManagers(managerid);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"管理员信息删除失败");
        }
    }

}
