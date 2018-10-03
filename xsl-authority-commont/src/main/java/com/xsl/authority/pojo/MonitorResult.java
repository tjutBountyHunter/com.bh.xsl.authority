package com.xsl.authority.pojo;

import java.util.List;

public class MonitorResult {
    private List<String> categories;
    private List<Integer> data;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
