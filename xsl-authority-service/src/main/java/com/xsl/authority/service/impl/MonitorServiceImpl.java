package com.xsl.authority.service.impl;

import com.xsl.authority.mapper.MonitorMapper;
import com.xsl.authority.pojo.MonitorResult;
import com.xsl.authority.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 权限监控Service层
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private MonitorMapper monitorMapper;

    /**
     * 展示用户权限调用数据
     * @return
     */
    @Override
    public MonitorResult loadUseData() {
        MonitorResult monitorResult = new MonitorResult();
        List<String> categories = monitorMapper.getAllRuleName();
        List<Integer> data = monitorMapper.getUseCountsByUrl();
        monitorResult.setCategories(categories);
        monitorResult.setData(data);
        return monitorResult;
    }

    /**
     * 展示用户权限分配数据
     * @return
     */
    @Override
    public MonitorResult loadAssginData() {
        MonitorResult monitorResult = new MonitorResult();
        List<String> categories = monitorMapper.getAllRuleName();
        List<Integer> data = monitorMapper.getAssginCountByUrl();
        monitorResult.setCategories(categories);
        monitorResult.setData(data);
        return monitorResult;
    }

    /**
     * 展示用户角色数目
     * @return
     */
    @Override
    public MonitorResult loadUserRoleCount() {
        MonitorResult monitorResult = new MonitorResult();
        //获取前几天的额数据
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");
        String date = df.format(new Date());
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)-1);
        String yesterday = df.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)-1);
        String yesterday1 = df.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)-1);
        String yesterday2 = df.format(calendar.getTime());
        List<String> categories = Arrays.asList(yesterday2,yesterday1,yesterday,date);
        List<Integer> data = Arrays.asList(1, 2, 3,4);
        monitorResult.setCategories(categories);
        monitorResult.setData(data);
        return monitorResult;
    }
}
