package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslAdmin;
import com.xsl.authority.pojo.XslAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XslAdminMapper {
    int countByExample(XslAdminExample example);

    int deleteByExample(XslAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XslAdmin record);

    int insertSelective(XslAdmin record);

    List<XslAdmin> selectByExample(XslAdminExample example);

    XslAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XslAdmin record, @Param("example") XslAdminExample example);

    int updateByExample(@Param("record") XslAdmin record, @Param("example") XslAdminExample example);

    int updateByPrimaryKeySelective(XslAdmin record);

    int updateByPrimaryKey(XslAdmin record);
}