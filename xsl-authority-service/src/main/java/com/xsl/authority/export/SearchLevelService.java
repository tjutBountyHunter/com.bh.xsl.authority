package com.xsl.authority.export;

import com.xsl.authority.utils.XslResult;

public interface SearchLevelService {

    //查询猎人的等级信息
    XslResult getHunterLevelInfo(Integer level);

    //查询雇主的等级信息
    XslResult getMasterLevelInfo(Integer level);

}
