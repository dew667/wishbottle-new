package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Wishbottle;

public interface WishbottleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wishbottle record);

    int insertSelective(Wishbottle record);

    Wishbottle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Wishbottle record);

    int updateByPrimaryKey(Wishbottle record);
}