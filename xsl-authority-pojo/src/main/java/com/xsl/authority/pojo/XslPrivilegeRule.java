package com.xsl.authority.pojo;

import java.util.ArrayList;
import java.util.List;

public class XslPrivilegeRule {
    private Integer id;
    private Integer pid;
    private String name;
    private String url;
    private String token;
    private short priority;
    private boolean open = true;
    private boolean checked = false;
    private List<XslPrivilegeRule> children = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public short getPriority() {
        return priority;
    }

    public void setPriority(short priority) {
        this.priority = priority;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<XslPrivilegeRule> getChildren() {
        return children;
    }

    public void setChildren(List<XslPrivilegeRule> children) {
        this.children = children;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
