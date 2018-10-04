package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslRole;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface ManagerRoleMapper {

    @Select("select id,name,state,createDate,updateDate from xsl_role")
    List<XslRole> getManagerRoles();

    void insertManagerRoles(HashMap map);

    void delteManagerRoles(HashMap map);

    @Select("SELECT roleid from xsl_manager_role WHERE managerid = #{managerid} and state = 0")
    List<Integer> getRoleidsByManagerid(Integer managerid);
}
