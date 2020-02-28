package com.hust13.wishbottle.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pick {

    private Integer id;

    private Integer wishbottleId;

    private Integer pickerId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date pickTime;

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

    public Integer getPickerId() {
        return pickerId;
    }

    public void setPickerId(Integer pickerId) {
        this.pickerId = pickerId;
    }

    public Date getPickTime() {
        return pickTime;
    }

    public void setPickTime(Date pickTime) {
        this.pickTime = pickTime;
    }
}
