package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Manager;

import java.util.List;

public interface ManagerService {

    //判断管理员是否是超级管理员
    public int judgeManager(int managerId);

    //添加管理员
    public int addManager(Manager manager);

    //删除管理员
    public int deleteManager(int managerId);

    //获取所有管理员信息
    public List<Manager> getManagers();
}
