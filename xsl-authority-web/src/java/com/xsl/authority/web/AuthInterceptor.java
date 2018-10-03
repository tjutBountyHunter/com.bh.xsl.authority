package com.xsl.authority.web;

import com.xsl.authority.mapper.LevelRuleMapper;
import com.xsl.authority.pojo.XslLevelRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LevelRuleMapper levelRuleMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 获取用户的请求地址
        String uri = request.getRequestURI();
        String path = request.getSession().getServletContext().getContextPath();
        // 判断当前路径是否需要进行权限验证。
        // 查询所有需要验证的路径集合
        List<XslLevelRule> xslLevelRules = levelRuleMapper.getAll();
        Set<String> uriSet = new HashSet<String>();
        for ( XslLevelRule xslLevelRule : xslLevelRules ) {
            if ( xslLevelRule.getUrl() != null && !"".equals(xslLevelRule.getUrl()) ) {
                uriSet.add(path + xslLevelRule.getUrl());
            }
        }
        if ( uriSet.contains(uri) ) {
            // 权限验证
            // 判断当前用户是否拥有对应的权限
            Set<String> authUriSet = (Set<String>)request.getSession().getAttribute("authUriSet");
            if ( authUriSet.contains(uri) ) {
                Integer usecount = levelRuleMapper.getUseCountByUrl(uri);
                usecount++;
                levelRuleMapper.updateUseCountByUrl(usecount,uri);
                return true;
            } else {
                response.sendRedirect(path + "/error");
                return false;
            }
        } else {
            return true;
        }
    }
}
