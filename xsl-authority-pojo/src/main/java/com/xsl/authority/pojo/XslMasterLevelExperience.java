package com.xsl.authority.pojo;

import java.io.Serializable;

public class XslMasterLevelExperience implements Serializable {
    private Integer id;

    private Integer masterlevelid;

    private Short level;

    private Short experience;

    private String createdate;

    private String updatedate;

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

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Short getExperience() {
        return experience;
    }

    public void setExperience(Short experience) {
        this.experience = experience;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }
}