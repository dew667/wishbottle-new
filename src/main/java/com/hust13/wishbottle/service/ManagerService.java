package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Manager;

import java.util.List;

/**
 * 管理员service接口
 */
public interface ManagerService {

    List<Manager> getAllManager();

    String deleteById(Integer id);

}
