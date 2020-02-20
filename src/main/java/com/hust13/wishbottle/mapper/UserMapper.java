package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.User;

import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    int insertOpenId(Map<String, String> map);

    User selectByPrimaryKey(Integer id);

    //由openid查对应userid
    int findUserIdByOpenId(String openid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}