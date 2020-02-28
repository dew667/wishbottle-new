package com.hust13.wishbottle.service;

import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.TreeComment;
import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.model.vo.CommentVO;
import com.hust13.wishbottle.model.vo.ReplyVO;

import java.util.List;

/**
 * 树洞评论回复Service接口
 * created by wzy on 2020/2/23
 */
public interface TreeReplyService {

    //获取所有评论
    List<CommentVO> getAllComments(Integer treeholeId);

    //获取作者评论
    PageInfo getAllCommentsOfAuthor(Integer treeholeId, Integer pageNum, Integer pageSize);

    //获取回复
    List<ReplyVO> getAllReplies(Integer commentId);

    TreeComment saveComment(TreeComment record);

    //保存回复
    TreeReply saveReply(TreeReply record);

    String giveLike(Integer commentId);

    String sendReport(Integer commentId);

}
