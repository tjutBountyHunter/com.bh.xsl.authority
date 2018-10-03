package com.xsl.authority.mapper;

import com.xsl.authority.pojo.XslAdmin;

import java.util.Map;

public interface UpdateMessage {

    //更新管理员信息
    void updateAdmin(XslAdmin xslAdmin);

    void updateState(Map map);
}