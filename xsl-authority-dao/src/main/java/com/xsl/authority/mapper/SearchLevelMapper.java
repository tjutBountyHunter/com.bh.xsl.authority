package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslHunterLevel;
import com.xsl.authority.pojo.XslMasterLevel;

public interface SearchLevelMapper {


    XslHunterLevel getHunterLevelInfo(Integer level);

    XslMasterLevel getMatserLevelInfo(Integer level);
}
