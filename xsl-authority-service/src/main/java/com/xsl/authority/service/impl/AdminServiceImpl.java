package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.*;
import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslAdmin;
import com.xsl.authority.service.AdminService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员Service
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private deleteMore deleteMore;

    @Autowired
    private XslAdminMapper xslAdminMapper;

    @Autowired
    private PageSearch pageSearch;

    @Autowired
    private UpdateMessage updateMessage;

    @Autowired
    private doAssgin doassgin;

    /**
     * 分页查询管理员信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @Override
    public PageDataResult getAdminList(String queryText, Integer pageno, Integer pagesize) {
        //取到分页信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageno - 1) * pagesize);
        map.put("size", pagesize);
        map.put("queryText", queryText);

        List<XslAdmin> adminList = pageSearch.getAdminList(map);
        // 当前页码
        // 总的数据条数
        int totalsize = pageSearch.getAdminTotalCount(map);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        PageDataResult<XslAdmin> adminPage = new PageDataResult<XslAdmin>();
        adminPage.setDatas(adminList);
        adminPage.setTotalno(totalno);
        adminPage.setTotalsize(totalsize);
        adminPage.setPageno(pageno);
        return adminPage;
    }

    /**
     * 新增管理员
     * @param xslAdmin
     * @return
     */
    @Override
    public XslResult insertAdmin(XslAdmin xslAdmin) {
        try {
            //补全管理员的pojo
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            xslAdmin.setCreatedate(time.format(new Date()));
            xslAdmin.setUpdatedate(time.format(new Date()));
            //1 为正常显示状态 2 为删除状态
            xslAdmin.setState((byte) 1);
            xslAdmin.setPassword("123465");
            xslAdminMapper.insert(xslAdmin);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改管理员信息
     * @param xslAdmin
     * @return
     */
    @Override
    public XslResult updateAdmin(XslAdmin xslAdmin) {

        try {
            //补全pojo
            //补全管理员的pojo
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            xslAdmin.setUpdatedate(time.format(new Date()));
            //进行查询
            updateMessage.updateAdmin(xslAdmin);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id查询管理员信息
     * @param id
     * @return
     */
    @Override
    public XslAdmin getAdminById(Integer id) {
        return xslAdminMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    @Override
    public XslResult deleteAdminById(Integer id) {
        try {
            Map map = new HashMap();
            //状态信息:0 删除 1 正常
            map.put("id",id);
            map.put("state",0);
            updateMessage.updateState(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id组删除管理员
     * @param adminid
     * @return
     */
    @Override
    public XslResult deleteAdmins(Integer[] adminid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("adminids", adminid);
            deleteMore.deleteAdmins(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public XslResult inseAdminRule(Integer adminid, Integer[] ruleids) {
        try {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            String date = time.format(new Date());
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("adminid",adminid);
            paramMap.put("ruleids",ruleids);
            paramMap.put("createdate",date);
            doassgin.deleteAdminRules(paramMap);
            doassgin.insertAdminRule(paramMap);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
