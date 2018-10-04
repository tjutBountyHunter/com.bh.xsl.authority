package com.xsl.authority.controller;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslRole;
import com.xsl.authority.service.ManagerRoleService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员信息Controller层
 */
@Controller
@RequestMapping("/managerrole")
public class ManagerRoleController {

    @Autowired
    private ManagerRoleService managerRoleService;


    /**
     * 展示管理员信息首页
     * @return
     */
    @RequestMapping("/index")
    public String showAdminPage(){
        return "managerrole/index";
    }


    /**
     * 分页查询管理员角色信息
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
            PageDataResult result = managerRoleService.getManagerRolesList(queryText,pageno,pagesize);
            return XslResult.build(200,"管理员角色信息分页查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"管理员角色信息分页信息查询失败");
        }
    }

    /**
     * 展示管理员角色添加页面
     * @return
     */
    @RequestMapping("/add")
    public String showAdminAddPage(){
        return "managerrole/add";
    }

    /**
     * 添加管理员
     * @param
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public XslResult insertMasterLevel(XslRole xslRole){
        XslResult xslResult = null;
        try {
            xslResult = managerRoleService.insertManagerRole(xslRole);
            return  xslResult;
        }catch (Exception e){
            return XslResult.build(500,"管理员角色信息添加失败，请重新操作");
        }
    }

    /**
     * 显示所要修改的管理员角色信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String showEditPage(Integer id, Model model){
        XslRole xslRole = managerRoleService.getManagerRoleById(id);
        model.addAttribute("role",xslRole);
        return "managerrole/edit";
    }

    /**
     * 修改管理员信息
     * @param xslRole
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public XslResult updateMasterLevel(XslRole xslRole){
        XslResult result = null;
        try {
            result =  managerRoleService.updateManagerRole(xslRole);
            return result;
        } catch ( Exception e ) {
            e.printStackTrace();
            return XslResult.build(500,"管理员角色信息修改失败，请重新操作");
        }
    }

    /**
     * 删除管理员角色
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public XslResult deleteUser(Integer id){
        XslResult result = null;
        try {
            result = managerRoleService.deleteManagerRoleById(id);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"管理员角色信息删除失败");
        }
    }
    /**
     * 删除多个用户信息
     * @param roleid
     * @return
     */
    @RequestMapping("/deletes")
    @ResponseBody
    public XslResult deleteHunterLevels(Integer []roleid){
        XslResult result = null;
        try {
            result = managerRoleService.deleteManagerRoles(roleid);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"管理员角色信息多选删除失败");
        }
    }


    /**
     * 跳转权限分配页面
     * @return
     */
    @RequestMapping("/managerassgin")
    public String showAssginPage(){
        return "managerrole/assgin";
    }

    /**
     * 为管理员分配权限
     * @param roleid
     * @param ruleids
     * @return
     */
    @RequestMapping("/doAssign")
    @ResponseBody
    public XslResult doAssgin(Integer roleid,Integer[] ruleids){
        XslResult xslResult = null;
        try {
            xslResult = managerRoleService.insertManagerRule(roleid,ruleids);
            System.out.println(xslResult.getStatus());
            return xslResult;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"权限分配失败");
        }
    }
}
