package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Manager;

import java.util.List;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    //获取所有
    List<Manager> getAllManager();

    int judgeManager(int id);

    Manager selectByAccount(String username);
}