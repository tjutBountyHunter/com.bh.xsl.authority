package com.xsl.authority.controller;

import com.xsl.authority.pojo.PageDataResult;
import com.xsl.authority.service.ManagerService;
import com.xsl.authority.utils.XslResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/managers")
public class ManagerController {
    @Resource
    private ManagerService managerService;

    /**
     * 展示管理员信息首页
     * @return
     */
    @RequestMapping("/index")
    public String showAdminPage(){
        return "manager/index";
    }

    /**
     * 分页查询管理员信息
     * @param queryText
     * @param pageno
     * @param pagesize
     * @return
     */
    @RequestMapping("/pageQuery")
    @ResponseBody
    public XslResult pageQuery(String queryText, Integer pageno, Integer pagesize){
        XslResult xslResult = new XslResult();
        try {
            PageDataResult result = managerService.getManagerist(queryText,pageno,pagesize);
            return XslResult.build(200,"管理员信息分页查询成功",result);
        }catch (Exception e){
            e.printStackTrace();
            return XslResult.build(500,"管理员信息分页信息查询失败");
        }
    }
}
