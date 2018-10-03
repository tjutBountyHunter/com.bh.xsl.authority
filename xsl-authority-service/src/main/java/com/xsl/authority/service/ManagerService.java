package com.xsl.authority.service;

import com.xsl.authority.pojo.PageDataResult;

public interface ManagerService {

    /**
     * //分页显示管理员信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    PageDataResult getManagerist(String queryText, Integer pageno, Integer pagesize);
}
