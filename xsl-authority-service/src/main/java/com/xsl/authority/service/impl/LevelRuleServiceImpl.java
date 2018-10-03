package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.LevelRuleMapper;
import com.xsl.authority.pojo.XslLevelRule;
import com.xsl.authority.service.LevelRuleService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.*;

/**
 * 权限管理Service层
 */
@Service
public class LevelRuleServiceImpl implements LevelRuleService {

    @Autowired
    private LevelRuleMapper levelRuleMapper;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource
    private Destination queueDestination;

    /**
     * 树形展示权限管理数据
     * @return
     */
    @Override
    public List<XslLevelRule> loadData() {
        List<XslLevelRule> rules = new ArrayList<>();
        //得到所有权限
        List<XslLevelRule> rs = levelRuleMapper.getAll();
        //读取树结构
        Map<Integer,XslLevelRule> xslLevelRuleMap = new HashMap<Integer, XslLevelRule>();
        for (XslLevelRule r:rs){
            xslLevelRuleMap.put(r.getId(),r);
        }

        for(XslLevelRule r : rs){
            XslLevelRule child = r;
            if(child.getPid()==0){
                rules.add(r);
            }else {
                XslLevelRule parent = xslLevelRuleMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return rules;
    }


    /**
     * 添加权限信息
     * @param xslLevelRule
     * @return
     */
    @Override
    public XslResult insertRules(XslLevelRule xslLevelRule) {
        if(xslLevelRule.getName()==null) {
            return XslResult.build(400, "权限名不能为空");
        }
        try {
            //补全pojo
            String token = "authorityToken:xslrule"+xslLevelRule.getPid()+"-"+UUID.randomUUID().toString();
            xslLevelRule.setToken(token);
            xslLevelRule.setUsecount(0);
            levelRuleMapper.insertRules(xslLevelRule);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id查询权限
     * @param id
     * @return
     */
    @Override
    public XslLevelRule getRuleById(Integer id) {
        return levelRuleMapper.getRuleById(id);
    }

    @Override
    public XslResult updateRule(XslLevelRule xslLevelRule) {
        if(xslLevelRule.getName()==null) {
            return XslResult.build(400, "权限名不能为空");
        }
        try {
            levelRuleMapper.updateRule(xslLevelRule);
            //使用Mq通知主系统权限系统做出了权限修改操作
            jmsTemplate.send(queueDestination,new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage("权限系统做出了权限修改操作");
                    return textMessage;
                }
            });
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id删除权限信息
     * @param id
     * @return
     */
    @Override
    public XslResult deleteRuleById(Integer id) {
        try {
            levelRuleMapper.deleteRuleById(id);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 回显已选权限
     * @return
     */
    @Override
    public List loadAssignData(Integer hunterlevelid) {
        List<XslLevelRule> rules = new ArrayList<>();
        List<XslLevelRule> rs = levelRuleMapper.getAll();
        //获取当前角色已经分配的权限
        List<Integer> ruleids = levelRuleMapper.getRuleIdsByRoleids(hunterlevelid);

        //读取树结构
        Map<Integer,XslLevelRule> xslLevelRuleMap = new HashMap<Integer, XslLevelRule>();
        for (XslLevelRule r:rs){
            if (ruleids.contains(r.getId())){
                r.setChecked(true);
            }else{
                r.setChecked(false);
            }
            //遍历得到所有节点
            xslLevelRuleMap.put(r.getId(),r);
        }
        for(XslLevelRule r : rs){
            XslLevelRule child = r;
            if(child.getPid()==0){
                rules.add(r);
            }else {
                XslLevelRule parent = xslLevelRuleMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return rules;
    }

    /**
     * 回显雇主等级角色已分配权限
     * @param masterlevelid
     * @return
     */
    @Override
    public List loadMasterAssignData(Integer masterlevelid) {
        List<XslLevelRule> rules = new ArrayList<>();
        List<XslLevelRule> rs = levelRuleMapper.getAll();
        //获取当前角色已经分配的权限
        List<Integer> ruleids = levelRuleMapper.getRuleIdsByMasterLevelids(masterlevelid);

        //读取树结构
        Map<Integer,XslLevelRule> xslLevelRuleMap = new HashMap<Integer, XslLevelRule>();
        for (XslLevelRule r:rs){
            if (ruleids.contains(r.getId())){
                r.setChecked(true);
            }else{
                r.setChecked(false);
            }
            //遍历得到所有节点
            xslLevelRuleMap.put(r.getId(),r);
        }
        for(XslLevelRule r : rs){
            XslLevelRule child = r;
            if(child.getPid()==0){
                rules.add(r);
            }else {
                XslLevelRule parent = xslLevelRuleMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return rules;
    }

    /**
     * 回显管理员已分配权限
     * @param adminid
     * @return
     */
    @Override
    public List<XslLevelRule> loadAdminAssignData(Integer adminid) {
        List<XslLevelRule> rules = new ArrayList<>();
        List<XslLevelRule> rs = levelRuleMapper.getAll();
        //获取当前角色已经分配的权限
        List<Integer> ruleids = levelRuleMapper.getRuleIdsByAdminlids(adminid);

        //读取树结构
        Map<Integer,XslLevelRule> xslLevelRuleMap = new HashMap<Integer, XslLevelRule>();
        for (XslLevelRule r:rs){
            if (ruleids.contains(r.getId())){
                r.setChecked(true);
            }else{
                r.setChecked(false);
            }
            //遍历得到所有节点
            xslLevelRuleMap.put(r.getId(),r);
        }
        for(XslLevelRule r : rs){
            XslLevelRule child = r;
            if(child.getPid()==0){
                rules.add(r);
            }else {
                XslLevelRule parent = xslLevelRuleMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        return rules;
    }

}
