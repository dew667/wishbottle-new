package com.hust13.wishbottle.entity;

import java.util.Date;

public class WishReply {
    private Integer id;

    private Integer wishbottleId;

    private Integer replyerId;

    private Date time;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWishbottleId() {
        return wishbottleId;
    }

    public void setWishbottleId(Integer wishbottleId) {
        this.wishbottleId = wishbottleId;
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
}