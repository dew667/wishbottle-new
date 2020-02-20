package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.TreeReply;

public interface TreeReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TreeReply record);

    int insertSelective(TreeReply record);

    TreeReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TreeReply record);

    int updateByPrimaryKey(TreeReply record);
}