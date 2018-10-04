package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.AuthorizateMapper;
import com.xsl.authority.pojo.XslLevelRule;
import com.xsl.authority.redis.JedisClient;
import com.xsl.authority.service.GetLevelRule;
import com.xsl.authority.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetLevelRuleImpl implements GetLevelRule {

    @Autowired
    private AuthorizateMapper authorizateMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${XSL_AUTHORITY_HUNTER_REDIS_KEY}")
    private String XSL_AUTHORITY_HUNTER_REDIS_KEY;

    @Value("${XSL_AUTHORITY_MASTER_REDIS_KEY}")
    private String XSL_AUTHORITY_MASTER_REDIS_KEY;

    @Value("${XSL_AUTHORITY_MANAGER_REDIS_KEY}")
    private String XSL_AUTHORITY_MANAGER_REDIS_KEY;

    @Override
    public List<XslLevelRule> getHunterRule(Integer hunterId) {
        try {
            String result = jedisClient.hget(XSL_AUTHORITY_HUNTER_REDIS_KEY,hunterId+"");
            if(!StringUtils.isBlank(result)){
                List<XslLevelRule> resultList = JsonUtils.jsonToList(result,XslLevelRule.class);
                return resultList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<XslLevelRule> xslLevelRules = authorizateMapper.getHunterRulesById(hunterId);
        try {
            String caheString = JsonUtils.objectToJson(xslLevelRules);
            System.out.println(caheString);
            jedisClient.hset(XSL_AUTHORITY_HUNTER_REDIS_KEY,hunterId+"",caheString);
        }catch (Exception e){
            e.printStackTrace();
        }
        return xslLevelRules;
    }

    @Override
    public List<XslLevelRule> getMatserRule(Integer masterId) {
        try {
            String result = jedisClient.hget(XSL_AUTHORITY_MASTER_REDIS_KEY,masterId+"");
            if(!StringUtils.isBlank(result)){
                List<XslLevelRule> resultList = JsonUtils.jsonToList(result,XslLevelRule.class);
                return resultList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<XslLevelRule> xslLevelRules = authorizateMapper.getMasterRulesById(masterId);
        try {
            String caheString = JsonUtils.objectToJson(xslLevelRules);
            jedisClient.hset(XSL_AUTHORITY_MASTER_REDIS_KEY,masterId+"",caheString);
        }catch (Exception e){
            e.printStackTrace();
        }
        return xslLevelRules;
    }

    /**
     * 管理员权限查询添加缓存
     * @param managerid
     * @return
     */
    @Override
    public List<XslLevelRule> getManagerRule(Integer managerid) {
        try {
            String result = jedisClient.hget(XSL_AUTHORITY_MANAGER_REDIS_KEY,managerid+"");
            if(!StringUtils.isBlank(result)){
                List<XslLevelRule> resultList = JsonUtils.jsonToList(result,XslLevelRule.class);
                return resultList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<XslLevelRule> xslLevelRules = authorizateMapper.getManagerRulesById(managerid);
        try {
            String caheString = JsonUtils.objectToJson(xslLevelRules);
            jedisClient.hset(XSL_AUTHORITY_MASTER_REDIS_KEY,managerid+"",caheString);
        }catch (Exception e){
            e.printStackTrace();
        }
        return xslLevelRules;
    }
}
