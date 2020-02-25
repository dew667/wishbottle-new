package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.TreeReply;

import java.util.List;
import java.util.Map;

public interface TreeReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TreeReply record);

    int insertSelective(TreeReply record);

    TreeReply selectByPrimaryKey(Integer id);

    //根据树洞文章id查询该文章所有评论
    List<TreeReply> searchAllComments(Integer treeholeId);

    //根据树洞文章id查询该文章所有评论 只看作者
    List<TreeReply> searchAllCommentsOfAuthor(Map map);

    //根据指定评论的id获取所有的回复
    List<TreeReply> searchAllReplies(Integer commentId);

    int updateByPrimaryKeySelective(TreeReply record);

    int updateByPrimaryKey(TreeReply record);

    //根据指定评论的id 更新点赞数目
    Integer updateLikesNum(Integer id);

    //根据指定的评论id 更新举报数目
    Integer updateReportNum(Integer id);

}