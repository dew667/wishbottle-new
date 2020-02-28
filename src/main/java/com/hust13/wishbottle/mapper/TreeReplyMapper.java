package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.model.vo.ReplyVO;

import java.util.List;

public interface TreeReplyMapper {

    //获取评论下面的所有回复
    List<ReplyVO> getAllReplies(Integer commentId);

    int insertSelective(TreeReply record);

}
