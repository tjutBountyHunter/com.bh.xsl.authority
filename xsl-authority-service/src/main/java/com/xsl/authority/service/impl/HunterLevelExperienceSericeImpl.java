package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.PageSearch;
import com.xsl.authority.mapper.XslHunterLevelExperienceMapper;
import com.xsl.authority.mapper.deleteMore;
import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslHunterLevelExperience;
import com.xsl.authority.service.HunterLevelExperienceService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 猎人等级经验信息管理service
 */
@Service
public class HunterLevelExperienceSericeImpl implements HunterLevelExperienceService {

    @Autowired
    private XslHunterLevelExperienceMapper xslHunterLevelExperienceMapper;

    @Autowired
    private PageSearch pageSearch;

    @Autowired
    private deleteMore deleteMore;

    /**
     * 分页查询猎人经验信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @Override
    public PageDataResult getHunterExperience(String queryText, Integer pageno, Integer pagesize) {
        //取到分页信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageno - 1) * pagesize);
        map.put("size", pagesize);
        map.put("queryText", queryText);

        List<XslHunterLevelExperience> xslHunterLevelExperiences = pageSearch.getHunterLevelExperience(map);
        // 当前页码
        // 总的数据条数
        int totalsize = pageSearch.getHunterLevelExperienceTotalCount(map);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        PageDataResult<XslHunterLevelExperience> hunterExperiencePage = new PageDataResult<XslHunterLevelExperience>();
        hunterExperiencePage.setDatas(xslHunterLevelExperiences);
        hunterExperiencePage.setTotalno(totalno);
        hunterExperiencePage.setTotalsize(totalsize);
        hunterExperiencePage.setPageno(pageno);
        return hunterExperiencePage;
    }

    /**
     * 添加猎人等级经验信息
     * @param xslHunterLevelExperience
     * @return
     */
    @Override
    public XslResult insertHunterLevelExperience(XslHunterLevelExperience xslHunterLevelExperience) {
        try {
            //补全雇主等级角色等级信息
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            xslHunterLevelExperience.setCreatedate(time.format(new Date()));
            xslHunterLevelExperience.setUpdatedate(time.format(new Date()));
            xslHunterLevelExperienceMapper.insert(xslHunterLevelExperience);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改猎人等级经验信息
     * @param xslHunterLevelExperience
     * @return
     */
    @Override
    public XslResult updaterHunterLevelExperience(XslHunterLevelExperience xslHunterLevelExperience){
        try {
            //补全pojo
            SimpleDateFormat updateTime = new SimpleDateFormat("yyyy-MM-dd");
            xslHunterLevelExperience.setUpdatedate(updateTime.format(new Date()));
            xslHunterLevelExperienceMapper.updateByPrimaryKey(xslHunterLevelExperience);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id查询猎人等级经验信息
     * @param id
     * @return
     */
    @Override
    public XslHunterLevelExperience getHunterLevelExperienceById(Integer id) {

        return xslHunterLevelExperienceMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过id删除猎人等级经验信息
     * @param id
     * @return
     */
    @Override
    public XslResult deleteHunterLevelExperienceById(Integer id) {
        try {
            xslHunterLevelExperienceMapper.deleteByPrimaryKey(id);
            return XslResult.ok();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id组来删除多个猎人等级经验性信息
     * @param hunterexperienceid
     * @return
     */
    @Override
    public XslResult deleteHunterLevelExperience(Integer[] hunterexperienceid) {
        try {
            System.out.println(hunterexperienceid.length);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("hunterexperienceids", hunterexperienceid);
            deleteMore.deleteHunterExperience(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
