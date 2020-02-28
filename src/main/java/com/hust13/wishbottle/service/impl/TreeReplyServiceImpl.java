package com.hust13.wishbottle.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.TreeComment;
import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.mapper.TreeCommentMapper;
import com.hust13.wishbottle.mapper.TreeReplyMapper;
import com.hust13.wishbottle.mapper.TreeholeMapper;
import com.hust13.wishbottle.model.vo.CommentVO;
import com.hust13.wishbottle.model.vo.ReplyVO;
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
    private TreeCommentMapper treeCommentMapper;

    @Autowired
    private TreeholeMapper treeholeMapper;

    @Autowired
    private TreeReplyMapper treeReplyMapper;

    /**
     * 根据树洞文章id获取所有评论
     * @param treeholeId
     * @return
     */
    @Override
    public List<CommentVO> getAllComments(Integer treeholeId) {
        return treeCommentMapper.searchAllComments(treeholeId);
    }

    /**
     * 只看作者的评论
     * @param treeholeId 树洞文章id
     * @return
     */
    @Override
    public PageInfo getAllCommentsOfAuthor(Integer treeholeId, Integer pageNum, Integer pageSize) {
        //查询文章对应的作者id
        Treehole record = treeholeMapper.selectByPrimaryKey(treeholeId);
        Integer writerId = record.getWriterId();
        //封装作者id和树洞文章id
        HashMap<String, Integer> map = new HashMap<>();
        map.put("treeholeId", treeholeId);
        map.put("writerId", writerId);
        //按时间升序排列
        String sort = "c.time asc";
        PageHelper.startPage(pageNum,pageSize,sort);
        PageInfo pageInfo = new PageInfo(treeCommentMapper.searchAllCommentsOfAuthor(map));
        return pageInfo;
    }

    /**
     * 根据指定的评论id查询所有回复
     * @param commentId
     * @return
     */
    @Override
    public List<ReplyVO> getAllReplies(Integer commentId) {
        return treeReplyMapper.getAllReplies(commentId);
    }

    /**
     * 保存评论
     * @param record
     * @return
     */
    @Override
    public TreeComment saveComment(TreeComment record) {
        //更新表中评论量
        Integer ret1 = treeholeMapper.updateCommentNum(record.getTreeholeId());
        Integer ret2 = treeCommentMapper.insertSelective(record);
        return record;
    }

    /**
     * 保存回复
     * @param record
     * @return
     */
    @Override
    public TreeReply saveReply(TreeReply record) {
        Integer ret = treeReplyMapper.insertSelective(record);
        return record;
    }

    /**
     * 点赞
     * @param commentId 评论id
     * @return
     */
    @Override
    public String giveLike(Integer commentId) {
        Integer ret = treeCommentMapper.updateLikesNum(commentId);
        if(ret > 0)
            return "点赞成功";
        else
            throw new RuntimeException("点赞操作失败");
    }

    /**
     * 对评论进行举报
     * @param commentId 评论id
     * @return
     */
    @Override
    public String sendReport(Integer commentId) {
        Integer ret = treeCommentMapper.updateReportNum(commentId);
        if(ret > 0)
            return "举报成功";
        else
            throw new RuntimeException("举报操作失败");
    }
}
