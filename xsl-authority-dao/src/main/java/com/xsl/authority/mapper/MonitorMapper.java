package com.xsl.authority.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MonitorMapper {
    @Select("SELECT name FROM xsl_rule")
    List<String> getAllRuleName();

    //查询没个权限的调用次数
    List<Integer> getUseCountsByUrl();

    //查询权限分配的次数
    List<Integer> getAssginCountByUrl();
}
