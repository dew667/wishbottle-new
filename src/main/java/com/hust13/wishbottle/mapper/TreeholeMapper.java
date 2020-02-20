package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Treehole;

public interface TreeholeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Treehole record);

    int insertSelective(Treehole record);

    Treehole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Treehole record);

    int updateByPrimaryKey(Treehole record);
}