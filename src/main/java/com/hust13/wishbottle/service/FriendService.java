package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Friend;
import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.model.vo.ConcernVO;

import java.util.List;
import java.util.Map;

/**
 * 好友-关注DAO接口
 * created by wzy on 2020/2/19
 */
public interface FriendService {

    Friend concernOneUser(Integer friendId, Integer mineId);

    List<ConcernVO> searchAllIConcern(Integer mineId);

    List<User> searchAllConcernMe(Integer mineId);

    String removeConcernItem(Integer friendId, Integer mineId);

    //判断是否关注某一用户
    Map<String, Integer> isConcernOneUser(Integer mineId, Integer userId);
}
