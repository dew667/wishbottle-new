package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.User;

/**
 * UserService
 * update by wzy on 2020/2/20
 */
public interface UserService {

    String userLogin(String js_code) throws Exception;

    Integer saveUserInfo(User userInfo, String openid) throws Exception;
}
