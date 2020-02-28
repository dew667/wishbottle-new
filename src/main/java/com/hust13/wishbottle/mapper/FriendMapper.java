package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Friend;
import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.model.vo.ConcernVO;

import java.util.List;
import java.util.Map;

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
    List<ConcernVO> searchAllIConcern(Integer mineId);

    /**
     * 查询我的所有粉丝
     * @param mineId
     * @return
     */
    List<User> searchAllConcernMe(Integer mineId);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);

    //判断表中是否已存在对应项
    Integer ifExist(Map<String, Integer> map);
}