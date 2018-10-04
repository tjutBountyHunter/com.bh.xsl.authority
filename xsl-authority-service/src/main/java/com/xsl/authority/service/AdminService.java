package com.xsl.authority.service;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslAdmin;
import com.xsl.authority.utils.XslResult;

public interface AdminService {
    /**
     * 分页显示管理员信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    PageDataResult getAdminList(String queryText, Integer pageno, Integer pagesize);

    /**
     * 新增管理员
     * @param xslAdmin
     * @return
     */
    XslResult insertAdmin(XslAdmin xslAdmin);

    /**
     * 修改管理员信息
     * @param xslAdmin
     * @return
     */
    XslResult updateAdmin(XslAdmin xslAdmin);

    /**
     * 通过id来查找管理员
     * @param id
     * @return
     */
    XslAdmin getAdminById(Integer id);

    /**
     * 通过id删除管理员
     * @param id
     * @return
     */
    XslResult deleteAdminById(Integer id);

    /**
     * 通过id组来多选删除
     * @param adminids
     * @return
     */
    XslResult deleteAdmins(Integer []adminids);

    /**
     * 分配权限信息
     * @param adminid
     * @param ruleids
     * @return
     */
    XslResult inseAdminRule(Integer adminid, Integer[] ruleids);
}
