package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.PageSearch;
import com.xsl.authority.mapper.XslHunterLevelMapper;
import com.xsl.authority.mapper.deleteMore;
import com.xsl.authority.mapper.doAssgin;
import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslHunterLevel;
import com.xsl.authority.pojo.XslHunterLevelExample;
import com.xsl.authority.service.HunterLevelService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 猎人角色等级管理Service
 */

@Service
public class HunterLevelServiceImpl implements HunterLevelService {

    @Autowired
    private deleteMore deleteMore;

    @Autowired
    private XslHunterLevelMapper xslHunterLevelMapper;

    @Autowired
    private PageSearch pageSearch;

    @Autowired
    private doAssgin doAssgin;


    /**
     * 分页查询猎人等级角色信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @Override
    public PageDataResult getHunterLevelList(String queryText, Integer pageno, Integer pagesize) {
        //取到分页信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageno - 1) * pagesize);
        map.put("size", pagesize);
        map.put("queryText", queryText);

        List<XslHunterLevel> hunterlevels = pageSearch.getHunterList(map);
        // 当前页码
        // 总的数据条数
        int totalsize = pageSearch.getHunterTotalCount(map);
        // 最大页码（总页码）
        int totalno = 0;
        if (totalsize % pagesize == 0) {
            totalno = totalsize / pagesize;
        } else {
            totalno = totalsize / pagesize + 1;
        }
        PageDataResult<XslHunterLevel> hunterLevelPage = new PageDataResult<XslHunterLevel>();
        hunterLevelPage.setDatas(hunterlevels);
        hunterLevelPage.setTotalno(totalno);
        hunterLevelPage.setTotalsize(totalsize);
        hunterLevelPage.setPageno(pageno);
        return hunterLevelPage;
    }

    /**
     * 添加猎人等级角色信息
     * @param xslHunterLevel
     * @return
     */
    @Override
    public XslResult insertHunterLevel(XslHunterLevel xslHunterLevel) {
        //添加查询条件
        XslHunterLevelExample example = new XslHunterLevelExample();
        XslHunterLevelExample.Criteria criteria = example.createCriteria();
        criteria.andLevelEqualTo(xslHunterLevel.getLevel());

        List<XslHunterLevel> list = xslHunterLevelMapper.selectByExample(example);
        if (list.size()>0&&list!=null){
            return XslResult.build(400,"添加猎人等级信息失败：等级重复");
        }
        example = new XslHunterLevelExample();
        criteria = example.createCriteria();
        criteria.andNameEqualTo(xslHunterLevel.getName());
        list = xslHunterLevelMapper.selectByExample(example);
        if (list.size()>0&&list!=null){
            return XslResult.build(400,"添加猎人等级信息失败：猎人名不能重复");
        }
        //补全雇主等级角色等级信息
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        xslHunterLevel.setCreatedate(time.format(new Date()));
        xslHunterLevel.setUpdatedate(time.format(new Date()));
        xslHunterLevelMapper.insert(xslHunterLevel);
        return XslResult.ok();
    }

    /**
     * 修改猎人等级信息
     * @param xslHunterLevel
     * @return
     */
    @Override
    public XslResult updateHunterLevel(XslHunterLevel xslHunterLevel) {
        try {
            //补全pojo
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            xslHunterLevel.setUpdatedate(time.format(new Date()));
            //进行修改
            xslHunterLevelMapper.updateByPrimaryKey(xslHunterLevel);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id查询猎人等级信息
     * @param id
     * @return
     */
    @Override
    public XslHunterLevel getHunterLevelById(Integer id) {
        return xslHunterLevelMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过id删除猎人等级信息
     * @param id
     * @return
     */
    @Override
    public XslResult deleteHunterLeveById(Integer id) {
        try {
            xslHunterLevelMapper.deleteByPrimaryKey(id);
            return XslResult.ok();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id组删除
     * @param hunterlevelid
     * @return
     */
    @Override
    public XslResult deleteHunterLevels(Integer[] hunterlevelid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("hunterlevelids", hunterlevelid);
            deleteMore.deleteHunterLevel(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给猎人角色等级进行权限分配
     * @param hunterlevelid
     * @param ruleids
     * @return
     */
    @Override
    public XslResult inserHunterLeveRule(Integer hunterlevelid, Integer[] ruleids) {

        try {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            String date = time.format(new Date());
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("hunterlevelid",hunterlevelid);
            paramMap.put("ruleids",ruleids);
            paramMap.put("createdate",date);
            doAssgin.deleteHunterLevelRules(paramMap);
            doAssgin.inserthunterlevelRule(paramMap);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给猎人等级角色进行特权分配
     * @param hunterlevelid
     * @param privilegeids
     * @return
     */
    @Override
    public XslResult inserHunterLevePrivilege(Integer hunterlevelid, Integer[] privilegeids) {
        try {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            String date = time.format(new Date());
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("hunterlevelid",hunterlevelid);
            paramMap.put("privilegeids",privilegeids);
            paramMap.put("createdate",date);
            doAssgin.deleteHunterLevelPrivilege(paramMap);
            doAssgin.inserthunterlevelPrivilege(paramMap);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
