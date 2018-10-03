package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslHunterLevel;
import com.xsl.authority.pojo.XslHunterLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XslHunterLevelMapper {
    int countByExample(XslHunterLevelExample example);

    int deleteByExample(XslHunterLevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslHunterLevel record);

    int insertSelective(XslHunterLevel record);

    List<XslHunterLevel> selectByExample(XslHunterLevelExample example);

    XslHunterLevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslHunterLevel record, @Param("example") XslHunterLevelExample example);

    int updateByExample(@Param("record") XslHunterLevel record, @Param("example") XslHunterLevelExample example);

    int updateByPrimaryKeySelective(XslHunterLevel record);

    int updateByPrimaryKey(XslHunterLevel record);
}