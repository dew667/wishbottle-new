package com.hust13.wishbottle.model.vo;

import com.hust13.wishbottle.entity.Pick;
import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.entity.Wishbottle;

/**
 * 心愿瓶列表返回结果封装类
 * create by wzy on 2020/2/27
 */
public class WishbottleVO extends Wishbottle {

    //关联用户属性
    private User user;

    //关联pick属性
    private Pick pick;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pick getPick() {
        return pick;
    }

    public void setPick(Pick pick) {
        this.pick = pick;
    }
}
