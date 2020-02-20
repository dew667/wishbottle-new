package com.hust13.wishbottle.entity;

/**
 * 好友-关注关系实体
 * created by wzy on 2020/2/19
 */
public class Friend {

    /**
     * 好友-关注条目id
     */
    private Integer id;

    /**
     * 关注者id
     */
    private Integer mineId;

    /**
     * 被关注者id
     */
    private Integer friendId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMineId() {
        return mineId;
    }

    public void setMineId(Integer mineId) {
        this.mineId = mineId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }
}