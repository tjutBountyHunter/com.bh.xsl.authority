package com.xsl.authority.service;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslManager;
import com.xsl.authority.utils.XslResult;

public interface ManagerService {

    /**
     * //分页显示管理员信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    PageDataResult getManagerist(String queryText, Integer pageno, Integer pagesize);

    /**
     * 新增管理员
     * @param xslManager
     * @return
     */
    XslResult insertManager(XslManager xslManager);

    /**
     * 修改管理员信息
     * @param xslManager
     * @return
     */
    XslResult updateManager(XslManager xslManager);

    /**
     * 通过id来查找管理员
     * @param id
     * @return
     */
    XslManager getManagerById(Integer id);

    /**
     * 通过id删除管理员
     * @param id
     * @return
     */
    XslResult deleteManagerById(Integer id);

    /**
     * 通过id组来多选删除
     * @param adminids
     * @return
     */
    XslResult deletManagers(Integer []adminids);

    /**
     * 分配权限信息
     * @param adminid
     * @param ruleids
     * @return
     */
//    XslResult insertManagerRule(Integer adminid, Integer[] ruleids);

    /**
     * 分配管理员角色
     * @param managerid
     * @param unassignroleids
     * @return
     */
    XslResult doRoleAssgin(Integer managerid, Integer[] unassignroleids);

    /**
     * 取消分配管理员角色
     * @param managerid
     * @param assginroleids
     * @return
     */
    XslResult dounRoleAssgin(Integer managerid, Integer[] assginroleids);

}
