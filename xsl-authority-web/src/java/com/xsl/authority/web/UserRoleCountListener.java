package com.xsl.authority.web;

import com.xsl.authority.pojo.MyTimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class UserRoleCountListener implements HttpSessionListener,ServletContextListener {

    // 定义一个锁，用于解决线程安全问题
    public Object lock = new Object();
    // 定义一个容器, 将 每次 创建的session 对象放到 容器中去保证线程安全
    private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("执行了，说明新创建了一个session对象。。。");

        HttpSession session = se.getSession();// 获取事件对象
        // 定义锁，解决线程安全问题
        synchronized (lock) {
            list.add(session);// 穿件的session放到容器中去
        }

        // long lastAccessedTime = session.getLastAccessedTime();//最后一次修改时间
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("执行了，说明销毁了一个session对象。。。");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized..............");
        // 定义一个定时器，并且在web应用启动时开始工作
        Timer timer = new Timer();

        // 安排指定的任务从指定的延迟后开始进行重复的固定延迟执行
        // task:安排的任务
        // delay:举例开始的指定的延时时间
        // period:重复时间
        //立刻 启动 定时器, 每隔 5 分钟 重复 执行 1000毫秒*60*5=5分钟
        timer.schedule(new MyTimerTask(list,lock), 0, 1000 * 60 * 5);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed......");
    }
}
