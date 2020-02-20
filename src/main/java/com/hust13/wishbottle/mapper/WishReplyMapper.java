package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.WishReply;

public interface WishReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WishReply record);

    int insertSelective(WishReply record);

    WishReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WishReply record);

    int updateByPrimaryKey(WishReply record);
}