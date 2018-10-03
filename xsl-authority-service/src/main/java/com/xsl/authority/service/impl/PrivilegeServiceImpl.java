package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.PrivilegeRuleMapper;
import com.xsl.authority.pojo.XslPrivilegeRule;
import com.xsl.authority.service.PrivilegeService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 特权管理Service层
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {


    @Autowired
    private PrivilegeRuleMapper privilegeRuleMapper;
    /**
     * 树形界面展示特权信息
     * @return
     */
    @Override
    public List<XslPrivilegeRule> loadData() {
        //声明特权信息列表
        List<XslPrivilegeRule> rules = new ArrayList<>();
        //取到所有特权
        List<XslPrivilegeRule> rs = privilegeRuleMapper.getAll();
        //读取树结构
        Map<Integer,XslPrivilegeRule> xslPrivilegeRuleMap = new HashMap<Integer, XslPrivilegeRule>();
        for (XslPrivilegeRule r:rs){
            xslPrivilegeRuleMap.put(r.getId(),r);
        }
        //遍历添加所有特权
        for(XslPrivilegeRule r : rs){
            XslPrivilegeRule child = r;
            //根源节点直接加入rules
            if(child.getPid()==0){
                rules.add(r);
            }else {
                XslPrivilegeRule parent = xslPrivilegeRuleMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return rules;
    }

    /**
     * 添加特权信息
     * @param xslPrivilegeRule
     * @return
     */
    @Override
    public XslResult insertPrivilegeRules(XslPrivilegeRule xslPrivilegeRule) {
        if(xslPrivilegeRule.getName()==null) {
            return XslResult.build(400, "特权名不能为空");
        }
        try {
            //补全pojo
             String token = "authorityToken:xslrule"+xslPrivilegeRule.getPid()+"-"+UUID.randomUUID().toString();
            xslPrivilegeRule.setToken(token);
            privilegeRuleMapper.insertPrivilegeRules(xslPrivilegeRule);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id查询特权信息
     * @param id
     * @return
     */
    @Override
    public XslPrivilegeRule getPrivilegeRuleById(Integer id) {
        return privilegeRuleMapper.getPrivilegeById(id);
    }

    /**
     * 修改特权信息
     * @param xslPrivilegeRule
     * @return
     */
    @Override
    public XslResult updatePrivilegeRule(XslPrivilegeRule xslPrivilegeRule) {
        if(xslPrivilegeRule.getName()==null) {
            return XslResult.build(400, "特权名不能为空");
        }
        try {
            privilegeRuleMapper.updatePrivilegeRule(xslPrivilegeRule);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id删除特权限信息
     * @param id
     * @return
     */
    @Override
    public XslResult deletePrivilegeRuleById(Integer id) {
        try {
            System.out.println(id);
            privilegeRuleMapper.deletePrivilegeRuleById(id);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 回显猎人特权信息
     * @param hunterlevelid
     * @return
     */
    @Override
    public List loadPrivilegeData(Integer hunterlevelid) {
        List<XslPrivilegeRule> rules = new ArrayList<>();
        List<XslPrivilegeRule> rs = privilegeRuleMapper.getAll();
        //获取当前角色已经分配的权限
        List<Integer> ruleids = privilegeRuleMapper.getPrivilegeIdsByRoleids(hunterlevelid);

        //读取树结构
        Map<Integer,XslPrivilegeRule> xslLevelPrivilegeMap = new HashMap<Integer, XslPrivilegeRule>();
        for (XslPrivilegeRule r:rs){
            if (ruleids.contains(r.getId())){
                r.setChecked(true);
            }else{
                r.setChecked(false);
            }
            //遍历得到所有节点
            xslLevelPrivilegeMap.put(r.getId(),r);
        }
        for(XslPrivilegeRule r : rs){
            XslPrivilegeRule child = r;
            if(child.getPid()==0){
                rules.add(r);
            }else {
                XslPrivilegeRule parent = xslLevelPrivilegeMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return rules;
    }

    @Override
    public List<XslPrivilegeRule> loadMasterPrivilegeData(Integer masterlevelid) {
        List<XslPrivilegeRule> rules = new ArrayList<>();
        List<XslPrivilegeRule> rs = privilegeRuleMapper.getAll();
        //获取当前角色已经分配的权限
        List<Integer> ruleids = privilegeRuleMapper.getMasterPrivilegeIdsByRoleids(masterlevelid);

        //读取树结构
        Map<Integer,XslPrivilegeRule> xslLevelPrivilegeMap = new HashMap<Integer, XslPrivilegeRule>();
        for (XslPrivilegeRule r:rs){
            if (ruleids.contains(r.getId())){
                r.setChecked(true);
            }else{
                r.setChecked(false);
            }
            //遍历得到所有节点
            xslLevelPrivilegeMap.put(r.getId(),r);
        }
        for(XslPrivilegeRule r : rs){
            XslPrivilegeRule child = r;
            if(child.getPid()==0){
                rules.add(r);
            }else {
                XslPrivilegeRule parent = xslLevelPrivilegeMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return rules;
    }

}
