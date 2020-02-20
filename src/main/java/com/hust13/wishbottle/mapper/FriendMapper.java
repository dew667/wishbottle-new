package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Friend;
import com.hust13.wishbottle.entity.User;

import java.util.List;

public interface FriendMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByFriendItem(Friend record);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer id);

    /**
     * 查询所有我关注的用户
     * @param mineId
     * @return
     */
    List<User> searchAllIConcern(Integer mineId);

    /**
     * 查询我的所有粉丝
     * @param mineId
     * @return
     */
    List<User> searchAllConcernMe(Integer mineId);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}