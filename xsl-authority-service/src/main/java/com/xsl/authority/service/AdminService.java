package com.xsl.authority.service;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslAdmin;
import com.xsl.authority.utils.XslResult;

public interface AdminService {
    /**
     * //分页显示管理员信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    PageDataResult getAdminList(String queryText, Integer pageno, Integer pagesize);

    //新增管理员
    XslResult insertAdmin(XslAdmin xslAdmin);

    //修改管理员信息
    XslResult updateAdmin(XslAdmin xslAdmin);

    //通过id来查找管理员
    XslAdmin getAdminById(Integer id);

    //通过id删除管理员
    XslResult deleteAdminById(Integer id);

    //通过id组来多选删除
    XslResult deleteAdmins(Integer []adminids);

    XslResult inseAdminRule(Integer adminid, Integer[] ruleids);
}
