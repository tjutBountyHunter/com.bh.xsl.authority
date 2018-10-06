package com.xsl.authority.controller;

import com.xsl.authority.pojo.JWTpojo;
import com.xsl.authority.pojo.Result;
import com.xsl.authority.pojo.XslManager;
import com.xsl.authority.redis.JedisClient;
import com.xsl.authority.utils.CookieUtils;
import com.xsl.authority.utils.JsonUtils;
import com.xsl.authority.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 访问权限管理首页
 */
@Controller
public class mainContorller {

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @Value("${SSO_URL}")
    private String SSO_URL;

    @Value("${TOKEN_EXPIRE_TIME}")
    private Integer TOKEN_EXPIRE_TIME;

    @Value("${XSL_MANAGER_INFO_KEY}")
    private String XSL_MANAGER_INFO_KEY;

    @Value("${TOKEN_KEY_PREFIX}")
    private String TOKEN_KEY_PREFIX;

    @Resource
    private JedisClient jedisClient;

    /**
     * 展示权限管理首页
     * @return
     */
    @RequestMapping("/")
    public String showmain(HttpServletRequest httpServletRequest){
        XslManager xslManager = getManagerInfo(httpServletRequest);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("xslManager",xslManager);
        return "main";
    }

    @RequestMapping("/error")
    public String showError(){return "error";}

    /**
     * 推出登陆
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String ManagerLogout(HttpServletRequest request, HttpServletResponse response){
        String tokenKey = CookieUtils.getCookieValue(request,TOKEN_KEY);
        setCookie(response,TOKEN_KEY,"");
        jedisClient.del(tokenKey);
        HttpSession session = request.getSession();
        session.invalidate();
        StringBuffer requestURL = request.getRequestURL();
        int index = requestURL.indexOf("/logout");
        requestURL.replace(index + 1,requestURL.length(),"");
        return "redirect:" + SSO_URL + "?returnUrl=" + requestURL ;
    }

    private void setCookie(HttpServletResponse response ,String name , String value){
        Cookie cookie = new Cookie(name , value);
        cookie.setDomain("47.93.230.61");
        cookie.setPath("/");
        cookie.setMaxAge(TOKEN_EXPIRE_TIME);
        response.addCookie(cookie);
    }

    private XslManager getManagerInfo(HttpServletRequest httpServletRequest){
        /**
         *
         * 功能描述: 获取已登录的用户信息。
         *
         * @param: [httpServletRequest, jedisClient]
         * @return: xsl.cms.pojo.XslManager
         * @auther: 11432_000
         * @date: 2018/10/6 10:07
         */
        String tokenKey = CookieUtils.getCookieValue(httpServletRequest, TOKEN_KEY);
        String token = jedisClient.get(TOKEN_KEY_PREFIX + tokenKey);
        Result payloadByToken = JwtUtils.getPayloadByToken(token);
        JWTpojo jwTpojo =(JWTpojo) payloadByToken.getData();
        Map<String, Object> extend = jwTpojo.getExtend();
        String managerInfoKey = extend.get("managerInfoKey").toString();
        String managerInfo = jedisClient.get(XSL_MANAGER_INFO_KEY + managerInfoKey);
        XslManager xslManager = JsonUtils.jsonToPojo(managerInfo, XslManager.class);
        return xslManager;
    }
}
