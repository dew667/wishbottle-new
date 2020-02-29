package com.hust13.wishbottle.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hust13.wishbottle.entity.Pick;
import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.entity.Wishbottle;

/**
 * 心愿瓶列表返回结果封装类
 * create by wzy on 2020/2/27
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WishbottleVO extends Wishbottle {

    //关联用户属性
    private User user;

    //关联pick属性
    private Pick pick;

    //计数器 显示有多少人阅读
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

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
