package com.xsl.authority.service;

import com.xsl.authority.pojo.XslGroupRule;
import com.xsl.authority.utils.XslResult;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GroupService {
    //加载权限数据
    @Select("SELECT id,groupid,ruleid,name,token FROM xsl_group_rule")
    List<XslGroupRule> loadData();

    //添加权限信息
    XslResult insertGroupRules(XslGroupRule xslGroupRule);

    //通过id得到权限
    @Select("SELECT id,pid,name,token,priority FROM xsl_group_rule WHERE id = #{id}")
    XslGroupRule getGroupRuleById(Integer id);
    //修改权限信息
    XslResult updateGroupRule(XslGroupRule xslGroupRule);
    //通过id删除权限信息
    XslResult deleteGroupRuleById(Integer id);
}
