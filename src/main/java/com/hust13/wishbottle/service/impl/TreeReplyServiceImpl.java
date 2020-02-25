package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.mapper.TreeReplyMapper;
import com.hust13.wishbottle.mapper.TreeholeMapper;
import com.hust13.wishbottle.service.TreeReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * 树洞评论回复Service实现类
 * created by wzy on 2020/2/23
 */
@Service
@Transactional
public class TreeReplyServiceImpl implements TreeReplyService {

    @Autowired
    TreeReplyMapper treeReplyMapper;

    @Autowired
    TreeholeMapper treeholeMapper;

    /**
     * 根据树洞文章id获取所有评论
     * @param treeholeId
     * @return
     */
    @Override
    public List<TreeReply> getAllComments(Integer treeholeId) {
        return treeReplyMapper.searchAllComments(treeholeId);
    }

    /**
     * 只看作者的评论
     * @param treeholeId 树洞文章id
     * @return
     */
    @Override
    public List<TreeReply> getAllCommentsOfAuthor(Integer treeholeId) {
        //查询文章对应的作者id
        Treehole record = treeholeMapper.selectByPrimaryKey(treeholeId);
        Integer writerId = record.getWriterId();
        //封装作者id和树洞文章id
        HashMap<String, Integer> map = new HashMap<>();
        map.put("treeholeId", treeholeId);
        map.put("writerId", writerId);
        return treeReplyMapper.searchAllCommentsOfAuthor(map);
    }

    /**
     * 根据指定的评论id查询所有回复
     * @param commentId
     * @return
     */
    @Override
    public List<TreeReply> getAllReplies(Integer commentId) {
        return treeReplyMapper.searchAllReplies(commentId);
    }

    /**
     * 保存评论
     * @param record
     * @return
     */
    @Override
    public TreeReply saveComment(TreeReply record) {
        //更新表中评论量
        Integer ret1 = treeholeMapper.updateCommentNum(record.getTreeholeId());
        Integer ret2 = treeReplyMapper.insertSelective(record);
        return record;
    }

    /**
     * 保存回复
     * @param record
     * @return
     */
    @Override
    public TreeReply saveReply(TreeReply record) {
        //更新表中评论量
        Integer ret1 = treeholeMapper.updateCommentNum(record.getTreeholeId());
        Integer ret2 = treeReplyMapper.insertSelective(record);
        return record;
    }

    /**
     * 点赞
     * @param commentId 评论id
     * @return
     */
    @Override
    public String giveLike(Integer commentId) {
        Integer ret = treeReplyMapper.updateLikesNum(commentId);
        if(ret > 0)
            return "点赞成功";
        else
            return null;
    }

    /**
     * 对评论进行举报
     * @param commentId 评论id
     * @return
     */
    @Override
    public String sendReport(Integer commentId) {
        Integer ret = treeReplyMapper.updateReportNum(commentId);
        if(ret > 0)
            return "举报成功";
        else
            return null;
    }
}
