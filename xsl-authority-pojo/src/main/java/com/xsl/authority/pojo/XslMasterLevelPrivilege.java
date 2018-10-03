package com.xsl.authority.pojo;

import java.util.Date;

public class XslMasterLevelPrivilege {
    private Integer id;

    private Integer masterlevelid;

    private Integer privilegeid;

    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterlevelid() {
        return masterlevelid;
    }

    public void setMasterlevelid(Integer masterlevelid) {
        this.masterlevelid = masterlevelid;
    }

    public Integer getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(Integer privilegeid) {
        this.privilegeid = privilegeid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}