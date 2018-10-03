package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.PageSearch;
import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslManager;
import com.xsl.authority.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private PageSearch pageSearch;

    @Override
    public PageDataResult getManagerist(String queryText, Integer pageno, Integer pagesize) {
        //取到分页信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageno - 1) * pagesize);
        map.put("size", pagesize);
        map.put("queryText", queryText);

        List<XslManager> managerList = pageSearch.getManagerList(map);
        // 当前页码
        // 总的数据条数
        int totalsize = pageSearch.getManagerTotalCount(map);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        PageDataResult<XslManager> managerPage = new PageDataResult<XslManager>();
        managerPage.setDatas(managerList);
        managerPage.setTotalno(totalno);
        managerPage.setTotalsize(totalsize);
        managerPage.setPageno(pageno);
        return managerPage;
    }
}
