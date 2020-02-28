package com.hust13.wishbottle.model.vo;

import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.entity.User;

/**
 * 树洞文章列表VO
 */
public class TreeholeVO extends Treehole {

    //关联User属性
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
