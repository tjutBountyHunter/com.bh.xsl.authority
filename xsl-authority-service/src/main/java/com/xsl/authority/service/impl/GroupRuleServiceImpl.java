package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.GroupRuleMapper;
import com.xsl.authority.mapper.LevelRuleMapper;
import com.xsl.authority.pojo.XslGroupRule;
import com.xsl.authority.service.GroupService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 权限组管理Service层
 */
@Service
public class GroupRuleServiceImpl implements GroupService {

    @Autowired
    private GroupRuleMapper groupRuleMapper;

    @Autowired
    private LevelRuleMapper levelRuleMapper;

    @Override
    public List<XslGroupRule> loadData() {
        List<XslGroupRule> rules = new ArrayList<>();
        List<XslGroupRule> rs = groupRuleMapper.getAll();
        //读取树结构
        Map<Integer,XslGroupRule> xslGroupRuleMap = new HashMap<Integer, XslGroupRule>();
        for (XslGroupRule r:rs){
            xslGroupRuleMap.put(r.getId(),r);

        }

        for(XslGroupRule r : rs){
            XslGroupRule child = r;
            if(child.getGroupid()==0){
                rules.add(r);
            }else {
                XslGroupRule group = xslGroupRuleMap.get(child.getGroupid());
                group.getChildren().add(child);
            }
        }
        return rules;
    }

    /**
     * 添加组权限
     * @param xslGroupRule
     * @return
     */
    @Override
    public XslResult insertGroupRules(XslGroupRule xslGroupRule) {
        if(xslGroupRule.getName()==null) {
            return XslResult.build(400, "权限名不能为空");
        }
        try {
            //补全pojo
            Integer ruleid = levelRuleMapper.getRuleIdByName(xslGroupRule.getName());
            String token = null;
            if(ruleid==null){
                xslGroupRule.setRuleid(0);
                token = "authorityToken:xslrule"+xslGroupRule.getGroupid()+"-"+UUID.randomUUID().toString();
            }else{
                xslGroupRule.setRuleid(ruleid);
                token = levelRuleMapper.getToken(ruleid);
            }
            xslGroupRule.setToken(token);
            groupRuleMapper.insertGroupRules(xslGroupRule);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 通过id得到权限组信息
     * @param id
     * @return
     */
    @Override
    public XslGroupRule getGroupRuleById(Integer id) {
        return groupRuleMapper.geGroupRuleById(id);
    }

    /**
     * 修改权限组信息
     * @param xslGroupRule
     * @return
     */
    @Override
    public XslResult updateGroupRule(XslGroupRule xslGroupRule) {

        try {
            if(xslGroupRule.getName()==null) {
              return XslResult.build(400, "权限名不能为空");
            }
            groupRuleMapper.updateGroupRule(xslGroupRule);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id删除权限组信息
     * @param id
     * @return
     */
    @Override
    public XslResult deleteGroupRuleById(Integer id) {
        try {
            System.out.println(id);
            groupRuleMapper.deleteGroupRuleById(id);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

