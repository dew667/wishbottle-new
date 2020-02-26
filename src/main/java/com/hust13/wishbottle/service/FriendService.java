package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Friend;
import com.hust13.wishbottle.entity.User;

import java.util.List;

/**
 * 好友-关注DAO接口
 * created by wzy on 2020/2/19
 */
public interface FriendService {

    Friend concernOneUser(Integer friendId, Integer mineId);

    List<User> searchAllIConcern(Integer mineId);

    List<User> searchAllConcernMe(Integer mineId);

    Integer removeConcernItem(Integer friendId, Integer mineId);
}
