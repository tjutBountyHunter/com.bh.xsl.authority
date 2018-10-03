package com.xsl.authority.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 访问权限管理首页
 */
@Controller
public class mainContorller {
    /**
     * 展示权限管理首页
     * @return
     */
    @RequestMapping("/")
    public String showmain(){
        return "main";
    }

    @RequestMapping("/error")
    public String showError(){return "error";}
}
