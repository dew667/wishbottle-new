package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Friend;
import com.hust13.wishbottle.entity.User;

import java.util.List;

/**
 * 好友-关注DAO接口
 * created by wzy on 2020/2/19
 */
public interface FriendService {

    Friend concernOneUser(Integer friendId, String openid);

    List<User> searchAllIConcern(String openid);

    List<User> searchAllConcernMe(String openid);

    Integer removeConcernItem(Integer friendId, String openid);
}
