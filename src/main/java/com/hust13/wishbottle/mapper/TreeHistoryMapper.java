package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.TreeHistory;

public interface TreeHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TreeHistory record);

    int insertSelective(TreeHistory record);

    TreeHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TreeHistory record);

    int updateByPrimaryKey(TreeHistory record);
}