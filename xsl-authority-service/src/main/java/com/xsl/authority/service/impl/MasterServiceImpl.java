package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.PageSearch;
import com.xsl.authority.mapper.XslMasterLevelMapper;
import com.xsl.authority.mapper.deleteMore;
import com.xsl.authority.mapper.doAssgin;
import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.pojo.XslMasterLevel;
import com.xsl.authority.pojo.XslMasterLevelExample;
import com.xsl.authority.service.MasterLevelService;
import com.xsl.authority.utils.XslResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 雇主等级角色Service层
 */
@Service
public class MasterServiceImpl implements MasterLevelService {

    @Autowired
    private deleteMore deleteMore;

    @Autowired
    private XslMasterLevelMapper xslMasterLevelMapper;

    @Autowired
    private PageSearch pageSearch;

    @Autowired
    private doAssgin doassgin;


    @Override
    public PageDataResult getMasterLevelList(String queryText, Integer pageno, Integer pagesize) {
        //取到分页信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (pageno - 1) * pagesize);
        map.put("size", pagesize);
        map.put("queryText", queryText);

        List<XslMasterLevel> masterlevelList = pageSearch.getMasterLevelList(map);
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
        PageDataResult<XslMasterLevel> masterlevelPage = new PageDataResult<XslMasterLevel>();
        masterlevelPage.setDatas(masterlevelList);
        masterlevelPage.setTotalno(totalno);
        masterlevelPage.setTotalsize(totalsize);
        masterlevelPage.setPageno(pageno);
        return masterlevelPage;
    }

    /**
     * 添加雇主角色等级信息
     * @param xslMasterLevel
     * @return
     */
    @Override
    public XslResult insertMasterLevel(XslMasterLevel xslMasterLevel) {
        try {
            //添加查询调价
            XslMasterLevelExample example = new XslMasterLevelExample();
            XslMasterLevelExample.Criteria criteria = example.createCriteria();
            criteria.andLevelEqualTo(xslMasterLevel.getLevel());
            List<XslMasterLevel> list = xslMasterLevelMapper.selectByExample(example);
            if (list.size()>0&&list!=null){
                return XslResult.build(400,"添加雇主等级信息失败：等级重复");
            }
            example = new XslMasterLevelExample();
            criteria = example.createCriteria();
            criteria.andNameEqualTo(xslMasterLevel.getName());
            list = xslMasterLevelMapper.selectByExample(example);
            if (list.size()>0&&list!=null){
                return XslResult.build(400,"添加雇主等级信息失败：等级名称重复");
            }
            //补全雇主等级角色等级信息
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            xslMasterLevel.setCreatedate(time.format(new Date()));
            xslMasterLevel.setUpdatedate(time.format(new Date()));
            xslMasterLevelMapper.insert(xslMasterLevel);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改雇主角色信息
     * @param xslMasterLevel
     * @return
     */
    @Override
    public XslResult updaterMasterLevel(XslMasterLevel xslMasterLevel) {
        try {
            //补全pojo
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            xslMasterLevel.setUpdatedate(time.format(new Date()));
            //进行查询
            xslMasterLevelMapper.updateByPrimaryKey(xslMasterLevel);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id查询雇主角色等级信息
     * @param id
     * @return
     */
    @Override
    public XslMasterLevel getMasterLevelById(Integer id) {
        return xslMasterLevelMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过雇主id来删除雇主等级角色信息
     * @param id
     * @return
     */
    @Override
    public XslResult deleteMasterLeveById(Integer id) {
        try {
            xslMasterLevelMapper.deleteByPrimaryKey(id);
            return XslResult.ok();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除多个猎人等级信信息
     * @param masterlevelid
     * @return
     */
    @Override
    public XslResult deleteMasterLevels(Integer []masterlevelid) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("masterlevelids", masterlevelid);
            deleteMore.deleteMasterLevel(map);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 权限分配
     * @param masterlevelid
     * @param ruleids
     * @return
     */
    @Override
    public XslResult inseMasterLeveRule(Integer masterlevelid, Integer[] ruleids) {

        try {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            String date = time.format(new Date());
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("masterlevelid",masterlevelid);
            paramMap.put("ruleids",ruleids);
            paramMap.put("createdate",date);
            //分配前删除所有已分配的权限信息
            doassgin.deleteMasterLevelRules(paramMap);
            //分配权限
            doassgin.insertMasterlevelRule(paramMap);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public XslResult inserMasterLevePrivilege(Integer masterlevelid, Integer[] privilegeids) {
        try {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            String date = time.format(new Date());
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("masterlevelid",masterlevelid);
            paramMap.put("privilegeids",privilegeids);
            paramMap.put("createdate",date);
            //分配前删除所有已分配的权限信息
            doassgin.deleteMasterLevelPrivilege(paramMap);
            //分配权限
            doassgin.insertMasterlevelPrivilege(paramMap);
            return XslResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
