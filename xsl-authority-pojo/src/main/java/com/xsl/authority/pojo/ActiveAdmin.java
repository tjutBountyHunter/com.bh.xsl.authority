package com.xsl.authority.pojo;

public class ActiveAdmin {
    private Integer adminid;
    private String adminacconunt;
    private String adminname;
    private String password;

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminacconunt() {
        return adminacconunt;
    }

    public void setAdminacconunt(String adminacconunt) {
        this.adminacconunt = adminacconunt;
    }
}
