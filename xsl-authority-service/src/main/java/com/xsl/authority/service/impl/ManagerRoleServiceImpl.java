package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.*;
import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslRole;
import com.xsl.authority.service.ManagerRoleService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerRoleServiceImpl implements ManagerRoleService {

    @Autowired
    private PageSearch pageSearch;

    @Autowired
    private XslRoleMapper xslRoleMapper;

    @Autowired
    private ManagerRoleMapper managerRoleMapper;

    @Autowired
    private UpdateMessage updateMessage;

    @Autowired
    private deleteMore deleteMore;

    @Autowired
    private doAssgin doassgin;

    /**
     * 分类已有权限何未分配权限
     * @param assginRoles
     * @param unassginRoles
     */
    @Override
    public void loadAssginRoles(List<XslRole> assginRoles, List<XslRole> unassginRoles,Integer managerid) {

        try {
            List<XslRole> xslRoles = managerRoleMapper.getManagerRoles();
            List<Integer> assginRoleids = managerRoleMapper.getRoleidsByManagerid(managerid);
            for(XslRole xslRole : xslRoles){
                if(assginRoleids.contains(xslRole.getId())){
                    assginRoles.add(xslRole);
                }else{
                    unassginRoles.add(xslRole);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 分页查询管理员角色信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @Override
    public PageDataResult getManagerRolesList(String queryText, Integer pageno, Integer pagesize) {
        //取到分页信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageno - 1) * pagesize);
        map.put("size", pagesize);
        map.put("queryText", queryText);

        List<XslRole> roleList = pageSearch.getManagerRolesList(map);
        // 当前页码
        // 总的数据条数
        int totalsize = pageSearch.getManagerRoleTotalCount(map);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        PageDataResult<XslRole> rolePage = new PageDataResult<XslRole>();
        rolePage.setDatas(roleList);
        rolePage.setTotalno(totalno);
        rolePage.setTotalsize(totalsize);
        rolePage.setPageno(pageno);
        return rolePage;
    }

    /**
     *
     * @param xslRole
     * @return
     */
    @Override
    public XslResult insertManagerRole(XslRole xslRole) {
        try {
            //补全管理员的pojo
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            xslRole.setCreatedate(time.format(new Date()));
            xslRole.setUpdatedate(time.format(new Date()));
            //0 为正常显示状态 1 为删除状态
            xslRole.setState((byte) 0);
            xslRoleMapper.insert(xslRole);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param xslRole
     * @return
     */
    @Override
    public XslResult updateManagerRole(XslRole xslRole) {
        try {
            //补全pojo
            //补全管理员的pojo
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            xslRole.setUpdatedate(time.format(new Date()));
            //进行查询
            updateMessage.updateManagerRole(xslRole);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id查询角色等级信息
     * @param id
     * @return
     */
    @Override
    public XslRole getManagerRoleById(Integer id) {
        return xslRoleMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过id来删除管理员角色信息
     * @param id
     * @return
     */
    @Override
    public XslResult deleteManagerRoleById(Integer id) {
        try {
            Map map = new HashMap();
            //状态信息:0 正常 1 删除
            map.put("id",id);
            map.put("state",1);
            updateMessage.deleteManagerRoleById(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id组来进行多选删除
     * @param roleid
     * @return
     */
    @Override
    public XslResult deleteManagerRoles(Integer[] roleid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("roleids", roleid);
            deleteMore.deleteManagerRoles(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给管理员权限分配权限
     * @param roleid
     * @param ruleids
     * @return
     */
    @Override
    public XslResult insertManagerRule(Integer roleid, Integer[] ruleids) {
        try {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            String date = time.format(new Date());
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("roleid",roleid);
            paramMap.put("ruleids",ruleids);
            paramMap.put("createdate",date);
            doassgin.deleteManagerRules(paramMap);
            doassgin.insertManagerRules(paramMap);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
