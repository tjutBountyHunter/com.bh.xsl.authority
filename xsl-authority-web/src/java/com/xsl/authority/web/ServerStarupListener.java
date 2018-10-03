package com.xsl.authority.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStarupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //将web应用名称保存到application范围中
       ServletContext application = sce.getServletContext();
       String path = application.getContextPath();
      application.setAttribute("APP_PATH",path);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
