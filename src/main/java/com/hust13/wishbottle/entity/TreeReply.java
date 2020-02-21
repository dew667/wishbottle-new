package com.hust13.wishbottle.entity;

import java.util.Date;

public class TreeReply {
    private Integer id;

    private Integer treeholeId;

    private Integer replyerId;

    private Integer answerId;

    private Date time;

    private String content;

    private Integer status;

    private Integer likes;

    private Integer version;

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

    public Integer getReplyerId() {
        return replyerId;
    }

    public void setReplyerId(Integer replyerId) {
        this.replyerId = replyerId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getlikes() {
        return likes;
    }

    public void setlikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}