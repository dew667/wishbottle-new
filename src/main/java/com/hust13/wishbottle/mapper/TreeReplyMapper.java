package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.TreeReply;

import java.util.List;

public interface TreeReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TreeReply record);

    int insertSelective(TreeReply record);

    TreeReply selectByPrimaryKey(Integer id);

    //根据树洞文章id查询该文章所有评论
    List<TreeReply> searchAllComments(Integer treeholeId);

    int updateByPrimaryKeySelective(TreeReply record);

    int updateByPrimaryKey(TreeReply record);
}