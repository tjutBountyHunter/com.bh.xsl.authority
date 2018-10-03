package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslMasterLevel;
import com.xsl.authority.pojo.XslMasterLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XslMasterLevelMapper {
    int countByExample(XslMasterLevelExample example);

    int deleteByExample(XslMasterLevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslMasterLevel record);

    int insertSelective(XslMasterLevel record);

    List<XslMasterLevel> selectByExample(XslMasterLevelExample example);

    XslMasterLevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslMasterLevel record, @Param("example") XslMasterLevelExample example);

    int updateByExample(@Param("record") XslMasterLevel record, @Param("example") XslMasterLevelExample example);

    int updateByPrimaryKeySelective(XslMasterLevel record);

    int updateByPrimaryKey(XslMasterLevel record);
}