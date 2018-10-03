package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslPrivilegeRule;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PrivilegeRuleMapper {
    //获取所有特权信息
    @Select("SELECT id,pid,name,url,token,priority  FROM xsl_privilege_rule")
    List<XslPrivilegeRule> getAll();

    //插入特权信息
    void insertPrivilegeRules(XslPrivilegeRule xslPrivilegeRule);

    //通过id获取特权信息
    @Select("SELECT id,pid,name,url,token,priority FROM xsl_privilege_rule WHERE id = #{id}")
    XslPrivilegeRule getPrivilegeById(Integer id);
    //修改猎人特权信息
    void updatePrivilegeRule(XslPrivilegeRule xslPrivilegeRule);
    //通过id删除猎人特权
    void deletePrivilegeRuleById(Integer id);

    //得到猎人特权信息
    @Select("select privilegeId from xsl_hunter_level_privilege where hunterlevelId = #{hunterlevelid}")
    List<Integer> getPrivilegeIdsByRoleids(Integer hunterlevelid);
    @Select("select privilegeId from xsl_master_level_privilege where masterlevelId = #{masterlevelid}")
    List<Integer> getMasterPrivilegeIdsByRoleids(Integer masterlevelid);
}
