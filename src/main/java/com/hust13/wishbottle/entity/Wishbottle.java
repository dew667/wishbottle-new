package com.hust13.wishbottle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wishbottle {
    private Integer id;

    private Integer writerId;

    //抛掷时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date throwTime;

    //修改草稿时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    private String title;

    private String content;

    //状态 0-心愿海 1-被捞取 2-发布者删除 3-管理员删除 4-保存在草稿箱
    private Integer status;

    //心愿瓶类型
    private Integer type;

    private Integer report;

    public Date getThrowTime() {
        return throwTime;
    }

    public void setThrowTime(Date throwTime) {
        this.throwTime = throwTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
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

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report;
    }
}