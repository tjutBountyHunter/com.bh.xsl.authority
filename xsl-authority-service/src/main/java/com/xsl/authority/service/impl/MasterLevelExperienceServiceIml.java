package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.PageSearch;
import com.xsl.authority.mapper.XslMasterLevelExperienceMapper;
import com.xsl.authority.mapper.deleteMore;
import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslMasterLevelExperience;
import com.xsl.authority.service.MasterLevelExperienceService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 猎人等级经验管理
 */
@Service
public class MasterLevelExperienceServiceIml implements MasterLevelExperienceService {

    @Autowired
    private XslMasterLevelExperienceMapper xslMasterLevelExperienceMapper;
    @Autowired
    private PageSearch pageSearch;
    @Autowired
    private deleteMore deleteMore;
    /**
     * 分页查询雇主经验信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @Override
    public PageDataResult getMasterExperience(String queryText, Integer pageno, Integer pagesize) {
        //取到分页信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageno - 1) * pagesize);
        map.put("size", pagesize);
        map.put("queryText", queryText);

        List<XslMasterLevelExperience> xslMasterLevelExperiences = pageSearch.getMasterExperience(map);
        // 当前页码
        // 总的数据条数
        int totalsize = pageSearch.getMasterLevelExperienceTotalCount(map);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        //创建分页对象
        PageDataResult<XslMasterLevelExperience> masterExperiencePage = new PageDataResult<XslMasterLevelExperience>();
        masterExperiencePage.setDatas(xslMasterLevelExperiences);
        masterExperiencePage.setTotalno(totalno);
        masterExperiencePage.setTotalsize(totalsize);
        masterExperiencePage.setPageno(pageno);
        return masterExperiencePage;
    }

    /**
     * 添加雇主等级经验信息
     * @param xslMasterLevelExperience
     * @return
     */
    @Override
    public XslResult insertMasterLevelExperience(XslMasterLevelExperience xslMasterLevelExperience) {
        try {
            //补全雇主等级角色等级信息
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            xslMasterLevelExperience.setCreatedate(time.format(new Date()));
            xslMasterLevelExperience.setUpdatedate(time.format(new Date()));
            xslMasterLevelExperienceMapper.insert(xslMasterLevelExperience);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改雇主等级经验信息
     * @param xslMasterLevelExperience
     * @return
     */
    @Override
    public XslResult updaterMasterLevelExperience(XslMasterLevelExperience xslMasterLevelExperience) {
        try {
            //补全pojo
            SimpleDateFormat updatetime = new SimpleDateFormat("yyyy-MM-dd");
            xslMasterLevelExperience.setUpdatedate(updatetime.format(new Date()));
            xslMasterLevelExperienceMapper.updateByPrimaryKey(xslMasterLevelExperience);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id查询雇主等级经验信息
     * @param id
     * @return
     */
    @Override
    public XslMasterLevelExperience getMasterLevelExperienceById(Integer id) {
        return xslMasterLevelExperienceMapper.selectByPrimaryKey(id);
    }

    /**
     *通过id删除雇主等级经验信息
     * @param id
     * @return
     */
    @Override
    public XslResult deleteMasterLevelExperienceById(Integer id) {
        try {
            xslMasterLevelExperienceMapper.deleteByPrimaryKey(id);
            return XslResult.ok();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除多个雇主等级经验信息
     * @param masterexperienceid
     * @return
     */
    @Override
    public XslResult deleteMasterLevelExperience(Integer[] masterexperienceid) {
        try {
            //以map的方式传递参数
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("masterexperienceids", masterexperienceid);
            //删除多个信息
            deleteMore.deleteMasterExperience(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
