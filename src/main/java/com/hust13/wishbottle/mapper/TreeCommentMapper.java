package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.TreeComment;
import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.model.vo.CommentVO;

import java.util.List;
import java.util.Map;

public interface TreeCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TreeComment record);

    int insertSelective(TreeComment record);

    TreeComment selectByPrimaryKey(Integer id);

    //根据树洞文章id查询该文章所有评论
    List<CommentVO> searchAllComments(Integer treeholeId);

    //根据树洞文章id查询该文章所有评论 只看作者
    List<CommentVO> searchAllCommentsOfAuthor(Map map);

    int updateByPrimaryKeySelective(TreeComment record);

    int updateByPrimaryKey(TreeComment record);

    //根据指定评论的id 更新点赞数目
    Integer updateLikesNum(Integer id);

    //根据指定的评论id 更新举报数目
    Integer updateReportNum(Integer id);

}