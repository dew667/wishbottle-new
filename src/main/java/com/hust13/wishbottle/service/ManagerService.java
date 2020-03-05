package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Manager;

import java.util.List;

/**
 * 管理员service接口
 */
public interface ManagerService {

    List<Manager> getAllManager();

    String deleteById(Integer id);

    //添加管理员
    public int addManager(Manager manager);

    //判断管理员是否是超级管理员
    public int judgeManager(int managerId);

    public int managerLogin(String account,String psd);

}
