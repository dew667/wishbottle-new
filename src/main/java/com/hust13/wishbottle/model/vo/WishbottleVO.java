package com.hust13.wishbottle.model.vo;

import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.entity.Wishbottle;

/**
 * 心愿瓶列表返回结果封装类
 * create by wzy on 2020/2/27
 */
public class WishbottleVO extends Wishbottle {

    //关联用户属性
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
