package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslUser;
import com.xsl.authority.pojo.XslUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XslUserMapper {
    int countByExample(XslUserExample example);

    int deleteByExample(XslUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslUser record);

    int insertSelective(XslUser record);

    List<XslUser> selectByExample(XslUserExample example);

    XslUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslUser record, @Param("example") XslUserExample example);

    int updateByExample(@Param("record") XslUser record, @Param("example") XslUserExample example);

    int updateByPrimaryKeySelective(XslUser record);

    int updateByPrimaryKey(XslUser record);
}