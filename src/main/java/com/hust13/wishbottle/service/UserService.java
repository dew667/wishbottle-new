package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.model.vo.UserVO;

import java.util.HashMap;
import java.util.List;

/**
 * UserService
 * update by wzy on 2020/2/20
 */
public interface UserService {

    Object userLogin(String js_code) throws Exception;

    User saveUserInfo(User userInfo, String openid);

    //由openid查询userid
    Integer getUserIdByOpenId(String openid);

    //获取指定用户信息
    UserVO getUserInfo(Integer userId);

    //修改用户信息
    UserVO updateUserInfo(UserVO record);

    //获取所有用户信息
    List<User> getAllUser();

    //解封用户
    String openById(Integer id);

    //封禁用户
    String closeById(Integer id);

    //统计用户性别
    List<HashMap<Integer,Integer>> genderCount();
    //统计用户年龄
    List<HashMap<String,Integer>> ageCount();
    //统计用户所属城市
    List<HashMap<String,Integer>> cityCount();
}
