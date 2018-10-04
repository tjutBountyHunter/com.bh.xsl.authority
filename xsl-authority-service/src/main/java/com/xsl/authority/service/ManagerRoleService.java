package com.xsl.authority.service;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslRole;
import com.xsl.authority.utils.XslResult;

import java.util.List;

public interface ManagerRoleService {


    /**
     * 分类已有权限和未分配权限
     * @param assginRoles
     * @param unassginRoles
     */
    void loadAssginRoles(List<XslRole>assginRoles,List<XslRole> unassginRoles,Integer managerid);

    /**
     * 分页显示管理员信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    PageDataResult getManagerRolesList(String queryText, Integer pageno, Integer pagesize);

    /**
     * 新增管理员角色
     * @param xslRole
     * @return
     */
    XslResult insertManagerRole(XslRole xslRole);

    /**
     * 修改管理员角色信息
     * @param xslRole
     * @return
     */
    XslResult updateManagerRole(XslRole xslRole);

    /**
     * 通过id来查找管理员角色
     * @param id
     * @return
     */
    XslRole getManagerRoleById(Integer id);

    /**
     * 通过id删除管理员角色
     * @param id
     * @return
     */
    XslResult deleteManagerRoleById(Integer id);

    /**
     * 通过id组来多选删除管理员角色
     * @param roleids
     * @return
     */
    XslResult deleteManagerRoles(Integer []roleids);

    /**
     * 分配权限信息
     * @param roleid
     * @param ruleids
     * @return
     */
    XslResult insertManagerRule(Integer roleid, Integer[] ruleids);

}
