package com.hust13.wishbottle.model.vo;

import com.hust13.wishbottle.entity.Friend;
import com.hust13.wishbottle.entity.User;

/**
 * 关注用户数据封装类
 */
public class ConcernVO extends Friend {

    //状态 1-只是我关注了他 2-双方互相关注
    private Integer status;

    //关联User属性
    private User user;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
