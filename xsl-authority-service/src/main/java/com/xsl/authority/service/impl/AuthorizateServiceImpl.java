package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.AuthorizateMapper;
import com.xsl.authority.mapper.LevelRuleMapper;
import com.xsl.authority.pojo.XslLevelRule;
import com.xsl.authority.service.AuthorizateService;
import com.xsl.authority.service.GetLevelRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class AuthorizateServiceImpl implements AuthorizateService {

    @Autowired
    private AuthorizateMapper authorizateMapper;

    @Autowired
    private LevelRuleMapper levelRuleMapper;

    @Autowired
    private GetLevelRule getLevelRule;

    /**
     * 获取管理员所拥有的权限
     * @param session
     * @param managerid
     */
    @Override
    public void AuthorityzatrByUrl(HttpSession session,Integer managerid) {
        List<XslLevelRule> xslLevelRules = getLevelRule.getManagerRule(managerid);
        Map<Integer,XslLevelRule> xslLevelRuleMap = new HashMap<Integer, XslLevelRule>();
        XslLevelRule root = null;
        Set<String> uriSet = new HashSet<String>();
        for(XslLevelRule xsllevelRule: xslLevelRules){
            xslLevelRuleMap.put(xsllevelRule.getId(),xsllevelRule);
            if(xsllevelRule.getUrl()!=null&&!"".equals(xsllevelRule.getUrl())){
                uriSet.add(session.getServletContext().getContextPath() + xsllevelRule.getUrl() );
            }
        }
        session.setAttribute("authUriSet",uriSet);
        for(XslLevelRule xslLevelRule:xslLevelRules){
            XslLevelRule child = xslLevelRule;
            if(child.getPid() == 0){
                root = xslLevelRule;
            }else{
                XslLevelRule parent = xslLevelRuleMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }
        session.setAttribute("rootXslLevelRule",root);
    }

    /**
     * 给猎人分配权限
     * @param session
     * @param hunterId
     */
    @Override
    public void AuthorityzatrHunterByUrl(HttpSession session, Integer hunterId) {
        int assgincount;
        //得到某猎人等级所拥有的权限
        List<XslLevelRule> xslLevelRules = getLevelRule.getHunterRule(hunterId);
        Map<Integer,XslLevelRule> xslLevelRuleMap = new HashMap<Integer, XslLevelRule>();
        XslLevelRule root = null;
        Set<String> uriSet = new HashSet<String>();
        for(XslLevelRule xsllevelRule: xslLevelRules){
            xslLevelRuleMap.put(xsllevelRule.getId(),xsllevelRule);
            if(xsllevelRule.getUrl()!=null&&!"".equals(xsllevelRule.getUrl())){
                uriSet.add(session.getServletContext().getContextPath() + xsllevelRule.getUrl());
            }
        }

        //将所拥有的权限存入session中
        session.setAttribute("authUriSet",uriSet);
        for(XslLevelRule xslLevelRule:xslLevelRules){
            XslLevelRule child = xslLevelRule;
            if(child.getPid() == 0){
                root = xslLevelRule;
            }else{
                XslLevelRule parent = xslLevelRuleMap.get(child.getPid());
                parent.getChildren().add(child);
                assgincount = levelRuleMapper.getAssginCountByUrl(child.getUrl());
                assgincount++;
                levelRuleMapper.updateAssginCountByUrl(assgincount,child.getUrl());
            }
        }
        session.setAttribute("rootXslLevelRule",root);
    }

    /**
     * 给雇主分配权限
     * @param session
     * @param masterId
     */
    @Override
    public void AuthorityzatrMatserByUrl(HttpSession session, Integer masterId) {
        int assgincount;
        //获取某雇主的所有权限
        List<XslLevelRule> xslLevelRules = getLevelRule.getMatserRule(masterId);
        Map<Integer,XslLevelRule> xslLevelRuleMap = new HashMap<Integer, XslLevelRule>();
        XslLevelRule root = null;
        Set<String> uriSet = new HashSet<String>();
        for(XslLevelRule xsllevelRule: xslLevelRules){
            xslLevelRuleMap.put(xsllevelRule.getId(),xsllevelRule);
            if(xsllevelRule.getUrl()!=null&&!"".equals(xsllevelRule.getUrl())){
                uriSet.add(session.getServletContext().getContextPath() + xsllevelRule.getUrl() );
            }
        }
        //将某雇主的权限写入session中
        session.setAttribute("authUriSet",uriSet);
        for(XslLevelRule xslLevelRule:xslLevelRules){
            XslLevelRule child = xslLevelRule;
            if(child.getPid() == 0){
                root = xslLevelRule;
            }else{
                XslLevelRule parent = xslLevelRuleMap.get(child.getPid());
                parent.getChildren().add(child);
                assgincount = levelRuleMapper.getAssginCountByUrl(child.getUrl());
                assgincount++;
                levelRuleMapper.updateAssginCountByUrl(assgincount,child.getUrl());
            }
        }
        session.setAttribute("rootXslLevelRule",root);
    }
}
