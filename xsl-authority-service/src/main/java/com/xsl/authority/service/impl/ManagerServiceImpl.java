package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.*;
import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslManager;
import com.xsl.authority.service.ManagerService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private PageSearch pageSearch;

    @Autowired
    private XslManagerMapper managerMapper;

    @Autowired
    private UpdateMessage updateMessage;

    @Autowired
    private deleteMore deleteMore;

    @Autowired
    private ManagerRoleMapper managerRoleMapper;

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

    /**
     * 新增管理员
     * @param xslManager
     * @return
     */
    @Override
    public XslResult insertManager(XslManager xslManager) {
        try {
            //补全管理员的pojo
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            //0正常 1冻结
            xslManager.setStatus((byte) 0);
            xslManager.setManagerPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            xslManager.setCreatTime(time.format(new Date()));
            xslManager.setRemoveTime("2018-01-01");
            xslManager.setLastLoginDate("2018-01-01");
            managerMapper.insert(xslManager);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改管理员信息
     * @param xslManager
     * @return
     */
    @Override
    public XslResult updateManager(XslManager xslManager) {

        try {
            String newpassword = DigestUtils.md5DigestAsHex(xslManager.getManagerPassword().getBytes());
            xslManager.setManagerPassword(newpassword);
            updateMessage.updateManager(xslManager);
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
    public XslManager getManagerById(Integer id) {
        return managerMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    @Override
    public XslResult deleteManagerById(Integer id) {
        try {
            Map map = new HashMap();
            //状态信息:0 删除 1 正常
            map.put("id",id);
            map.put("status",1);
            updateMessage.updateManagerState(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id组删除管理员
     * @param managerid
     * @return
     */
    @Override
    public XslResult deletManagers(Integer[] managerid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("managerids", managerid);
            deleteMore.deleteManagers(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public XslResult doRoleAssgin(Integer managerid, Integer[] unassignroleids) {

        try {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            String date = time.format(new Date());
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("managerid",managerid);
            map.put("roleids",unassignroleids);
            map.put("createdate",date);
            map.put("updatedate",date);
            managerRoleMapper.insertManagerRoles(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public XslResult dounRoleAssgin(Integer managerid, Integer[] assginroleids) {

        try {
            //删除角色管理表数据
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("managerid",managerid);
            map.put("roleids",assginroleids);
            managerRoleMapper.delteManagerRoles(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public XslResult insertManagerRule(Integer adminid, Integer[] ruleids) {
//        try {
//            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
//            String date = time.format(new Date());
//            Map<String,Object> paramMap = new HashMap<String,Object>();
//            paramMap.put("adminid",adminid);
//            paramMap.put("ruleids",ruleids);
//            paramMap.put("createdate",date);
//            doassgin.deleteAdminRules(paramMap);
//            doassgin.insertAdminRule(paramMap);
//            return XslResult.ok();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
}
