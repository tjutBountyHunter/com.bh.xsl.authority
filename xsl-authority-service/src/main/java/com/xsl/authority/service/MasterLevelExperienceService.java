package com.xsl.authority.service;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslMasterLevelExperience;
import com.xsl.authority.utils.XslResult;

public interface MasterLevelExperienceService {


    //分页雇主等级经验信息
    PageDataResult getMasterExperience(String queryText, Integer pageno, Integer pagesize);

    //新增雇主等级经验信息
    XslResult insertMasterLevelExperience(XslMasterLevelExperience xslMasterLevelExperience);

    //修改雇主等级经验信息
    XslResult updaterMasterLevelExperience(XslMasterLevelExperience xslMasterLevelExperience);

    //通过id来查找雇主等级经验信息
    XslMasterLevelExperience getMasterLevelExperienceById(Integer id);

    //通过id删除雇主等级经验信息
    XslResult deleteMasterLevelExperienceById(Integer id);
    //删除多个雇主等级经验信息
    XslResult deleteMasterLevelExperience(Integer[] masterexperienceid);

}
