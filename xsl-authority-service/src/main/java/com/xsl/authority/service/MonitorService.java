package com.xsl.authority.service;

import com.xsl.authority.pojo.MonitorResult;

public interface MonitorService {

    MonitorResult loadUseData();

    MonitorResult loadAssginData();

    MonitorResult loadUserRoleCount();
}
