package com.hust13.wishbottle.model.vo;

import com.hust13.wishbottle.entity.TreeComment;
import com.hust13.wishbottle.entity.User;

/**
 * 评论VO封装类
 */
public class CommentVO extends TreeComment {

    //关联User属性
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
