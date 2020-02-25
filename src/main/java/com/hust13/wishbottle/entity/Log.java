package com.hust13.wishbottle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Log {
    private Integer id;

    private Integer writerId;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date time;

    private Integer status;

    private String title;

    //心情
    private Integer emotion;

    private String weather;

    public Integer getEmotion() {
        return emotion;
    }

    public void setEmotion(Integer emotion) {
        this.emotion = emotion;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}