package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.TreeReply;

import java.util.List;

/**
 * 树洞评论回复Service接口
 * created by wzy on 2020/2/23
 */
public interface TreeReplyService {

    List<TreeReply> getAllComments(Integer treeholeId);

    List<TreeReply> getAllReplies(Integer commentId);

    TreeReply saveComment(TreeReply record);

    TreeReply saveReply(TreeReply record);

    String giveLike(Integer commentId);

    String sendReport(Integer commentId);

}
