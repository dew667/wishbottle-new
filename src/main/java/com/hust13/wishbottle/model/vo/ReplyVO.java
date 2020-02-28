package com.hust13.wishbottle.model.vo;

import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.entity.User;

/**
 * 回复封装类
 */
public class ReplyVO extends TreeReply {

    //关联User属性
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
