package com.hust13.wishbottle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeComment {
    private Integer id;

    private Integer treeholeId;

    private Integer replyerId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date time;

    private String content;

    private Integer likes;

    private Integer report;

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report;
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

    public Integer getReplyerId() {
        return replyerId;
    }

    public void setReplyerId(Integer replyerId) {
        this.replyerId = replyerId;
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

    public Integer getlikes() {
        return likes;
    }

    public void setlikes(Integer likes) {
        this.likes = likes;
    }

}