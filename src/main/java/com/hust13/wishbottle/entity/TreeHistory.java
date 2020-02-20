package com.hust13.wishbottle.entity;

import java.util.Date;

public class TreeHistory {
    private Integer id;

    private Integer treeholeId;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTreeholeId() {
        return treeholeId;
    }

    public void setTreeholeId(Integer treeholeId) {
        this.treeholeId = treeholeId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}