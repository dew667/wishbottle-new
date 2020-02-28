package com.hust13.wishbottle.model.vo;

import com.hust13.wishbottle.entity.TreeHistory;
import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.entity.User;

/**
 * 浏览记录封装类 用于查询浏览记录的返回数据
 */
public class HistoryVO extends TreeHistory {

    //关联User属性
    private User user;

    //关联Treehole属性
    private Treehole treehole;


    public Treehole getTreehole() {
        return treehole;
    }

    public void setTreehole(Treehole treehole) {
        this.treehole = treehole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
