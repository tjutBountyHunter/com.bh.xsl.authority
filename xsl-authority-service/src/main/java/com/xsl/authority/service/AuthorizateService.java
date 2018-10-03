package com.xsl.authority.service;

import javax.servlet.http.HttpSession;

public interface AuthorizateService {

    //通过session获取管理员的权限url
    void AuthorityzatrByUrl(HttpSession session,Integer id);
    //通过session获取猎人的权限url
    void AuthorityzatrHunterByUrl(HttpSession session,Integer id);
    //通过session来获取雇主的权限url
    void AuthorityzatrMatserByUrl(HttpSession session,Integer id);
}
