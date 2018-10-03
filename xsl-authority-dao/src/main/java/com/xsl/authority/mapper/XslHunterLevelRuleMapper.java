package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslHunterLevelRule;
import com.xsl.authority.pojo.XslHunterLevelRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XslHunterLevelRuleMapper {
    int countByExample(XslHunterLevelRuleExample example);

    int deleteByExample(XslHunterLevelRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslHunterLevelRule record);

    int insertSelective(XslHunterLevelRule record);

    List<XslHunterLevelRule> selectByExample(XslHunterLevelRuleExample example);

    XslHunterLevelRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslHunterLevelRule record, @Param("example") XslHunterLevelRuleExample example);

    int updateByExample(@Param("record") XslHunterLevelRule record, @Param("example") XslHunterLevelRuleExample example);

    int updateByPrimaryKeySelective(XslHunterLevelRule record);

    int updateByPrimaryKey(XslHunterLevelRule record);
}