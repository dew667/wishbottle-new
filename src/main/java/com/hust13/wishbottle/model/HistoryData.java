package com.hust13.wishbottle.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 浏览记录封装类 用于查询浏览记录的返回数据
 */
public class HistoryData {

    //树洞文章id
    private Integer id;

    //作者id
    private Integer writer_id;

    //文章表题
    private String title;

    //浏览时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(Integer writer_id) {
        this.writer_id = writer_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
