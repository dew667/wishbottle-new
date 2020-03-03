package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    int insertOpenId(Map<String, Object> map);

    User selectByPrimaryKey(Integer id);

    //由openid查对应userid
    Integer findUserIdByOpenId(String openid);

    //选择性更新session表
    Integer updateSessionSelective(Map<String, Object> map);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //获取用户信息
    User getUserInfo(Integer userId);

    List<User> getAllUser();

    Integer ifExist(Integer userId);
}