package com.hust13.wishbottle.model.vo;

import com.hust13.wishbottle.entity.Tag;
import com.hust13.wishbottle.entity.User;

import java.util.List;

/**
 * 用户信息VO
 */
public class UserVO extends User {

   private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
