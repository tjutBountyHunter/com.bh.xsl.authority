package com.xsl.authority.controller;

import com.xsl.authority.pojo.MonitorResult;
import com.xsl.authority.service.MonitorService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 监控模块Controller
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @Resource
    private MonitorService monitorService;

    /**
     * 展示权限调用监控界面
     * @return
     */
    @RequestMapping("/use")
    public String showMonitorUse(){
        return "monitor/use";
    }

    /**
     * 展示权限调用数据
     * @return
     */
    @RequestMapping("/monitoruse")
    @ResponseBody
    public XslResult loadUseData(){
        XslResult xslResult = new XslResult();
        try {
            MonitorResult monitorResult = monitorService.loadUseData();
            xslResult.setData(monitorResult);
            xslResult.setStatus(200);
            return xslResult;
        }catch (Exception e){
            e.printStackTrace();
            XslResult.build(400,"展示权限调用情况失败");
        }
        return null;
    }

    /**
     * 展示用户角色数目界面
     * @return
     */
    @RequestMapping("userolecount")
    public String showUserRoleCount(){
        return "monitor/userolecount";
    }

    /**
     * 展示用户角色数目情况
     * @return
     */
    @RequestMapping("/loaduserolecount")
    @ResponseBody
    public XslResult loadUserRoleCount(){
        XslResult xslResult = new XslResult();
        try {
            MonitorResult monitorResult = monitorService.loadUserRoleCount();
            xslResult.setData(monitorResult);
            xslResult.setStatus(200);
            return xslResult;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"用户角色数目展示失败");
        }
    }

    /**
     * 展示权限分配情况界面
     * @return
     */
    @RequestMapping("/assgin")
    public String showAuthorityAssgin(){
        return "monitor/assgin";
    }

    @RequestMapping("/monitorassgin")
    @ResponseBody
    public XslResult loadMonitorAssgin(){
        XslResult xslResult = new XslResult();
        try {
            MonitorResult monitorResult = monitorService.loadAssginData();
            xslResult.setData(monitorResult);
            xslResult.setStatus(200);
            return xslResult;
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(400,"加载权限分配情况失败");
        }
    }
}
