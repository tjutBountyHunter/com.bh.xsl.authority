package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslHunterLevelExperience;
import com.xsl.authority.pojo.XslHunterLevelExperienceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XslHunterLevelExperienceMapper {
    int countByExample(XslHunterLevelExperienceExample example);

    int deleteByExample(XslHunterLevelExperienceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslHunterLevelExperience record);

    int insertSelective(XslHunterLevelExperience record);

    List<XslHunterLevelExperience> selectByExample(XslHunterLevelExperienceExample example);

    XslHunterLevelExperience selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslHunterLevelExperience record, @Param("example") XslHunterLevelExperienceExample example);

    int updateByExample(@Param("record") XslHunterLevelExperience record, @Param("example") XslHunterLevelExperienceExample example);

    int updateByPrimaryKeySelective(XslHunterLevelExperience record);

    int updateByPrimaryKey(XslHunterLevelExperience record);
}