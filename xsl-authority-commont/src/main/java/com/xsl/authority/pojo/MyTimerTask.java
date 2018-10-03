package com.xsl.authority.pojo;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ListIterator;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    private List<HttpSession> list;// session容器
    private Object lock;//锁，从SessionScanner中 传递而来

    public MyTimerTask(List<HttpSession> list, Object lock) {
        this.list = list;// 获取session容器
        this.lock=lock;//锁，从SessionScanner中 传递而来
    }


    @Override
    public void run() {
        // 定义锁，解决线程安全问题
        synchronized (lock) {
            // 遍历session容器
            ListIterator<HttpSession> it = list.listIterator();
            while (it.hasNext()) {
                HttpSession session = it.next();
                // 遍历 list , 拿到 每个session 对象, 判断session的最后一次访问时间 与当前时间 的间隔是否超过 5 分钟, 如果超过就手动销毁

                if (session.getLastAccessedTime() - 1000 * 60 * 5 > 0) {
                    session.invalidate();// 销毁session
                    list.remove(session);// 从session容器中销毁session对象
                }
            }
        }

    }
}
