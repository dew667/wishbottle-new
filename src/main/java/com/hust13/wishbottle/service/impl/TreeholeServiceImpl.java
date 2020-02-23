package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.mapper.TreeReplyMapper;
import com.hust13.wishbottle.mapper.TreeholeMapper;
import com.hust13.wishbottle.service.TreeholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 树洞Service实现类
 * update by wzy on 2020/2/21
 */
@Service
@Transactional
public class TreeholeServiceImpl implements TreeholeService {

    @Autowired
    TreeholeMapper treeholeMapper;

    @Autowired
    TreeReplyMapper treeReplyMapper;

    /**
     * 搜索所有文章列表
     * @return
     */
    @Override
    public List<Treehole> searchArticleList() {
        return treeholeMapper.searchAllArticle();
    }

    /**
     * 保存树洞文章条目
     * @param record
     * @return
     */
    @Override
    public Treehole saveTreeholeArticle(Treehole record) {
        //获取时间
        Date date = new Date();
        record.setTime(date);
        //初始化数据
        record.setViews(0);
        record.setlikes(0);
        record.setReport(0);
        record.setStatus(1);
        record.setTop(0);
        record.setReplyNum(0);
        Integer ret = treeholeMapper.insertSelective(record);
        return record;
    }

    /**
     * 获取单篇树洞文章
     * @param id
     * @return
     */
    @Override
    public Treehole getOneArticle(Integer id) {
        return treeholeMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据树洞文章id获取所有评论
     * @param treeholeId
     * @return
     */
    @Override
    public List<TreeReply> getAllComments(Integer treeholeId) {
        return treeReplyMapper.searchAllComments(treeholeId);
    }
}
