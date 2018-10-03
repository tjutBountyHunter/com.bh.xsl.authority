package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslGroupRule;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GroupRuleMapper {
    int getlost();
    //获取组特权信息
    @Select("SELECT id,groupId,ruleid,name,url,token  FROM xsl_group_rule")
    List<XslGroupRule> getAll();

    //插入权限信息
    void insertGroupRules(XslGroupRule xslGroupRule);

    //通过id获取权限信息
    @Select("SELECT id,groupId,ruleid,name,url,token FROM xsl_group_rule WHERE id = #{id}")
    XslGroupRule geGroupRuleById(Integer id);

    void updateGroupRule(XslGroupRule xslGroupRule);

    void deleteGroupRuleById(Integer id);

}
