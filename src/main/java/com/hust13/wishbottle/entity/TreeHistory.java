package com.hust13.wishbottle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeHistory {
    private Integer id;

    private Integer treeholeId;

    private Integer userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date time;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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