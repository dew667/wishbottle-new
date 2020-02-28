package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.TreeHistory;
import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.mapper.TreeHistoryMapper;
import com.hust13.wishbottle.mapper.TreeholeMapper;
import com.hust13.wishbottle.model.vo.HistoryVO;
import com.hust13.wishbottle.model.vo.TreeholeVO;
import com.hust13.wishbottle.service.TreeholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private TreeholeMapper treeholeMapper;

    @Autowired
    private TreeHistoryMapper treeHistoryMapper;

    /**
     * 搜索所有文章列表
     * @return
     */
    @Override
    public List<TreeholeVO> searchArticleList() {
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
        record.setTop(0);
        record.setReplyNum(0);
        Integer ret = treeholeMapper.insertSelective(record);
        return record;
    }

    /**
     * 获取单篇树洞文章
     * @param treeholeId
     * @return
     */
    @Override
    public TreeholeVO getOneArticle(Integer treeholeId) {
        //更新treehole表中views字段 阅读量+1
        Integer ret1 = treeholeMapper.updateArticleViews(treeholeId);
        //返回树洞文章
        return treeholeMapper.getArticleInfo(treeholeId);
    }

    /**
     * 点赞数增加
     * @param treeholeId 树洞id
     * @return
     */
    @Override
    public String giveLike(Integer treeholeId) {
        Integer ret = treeholeMapper.updateLikesNum(treeholeId);
        if(ret > 0)
            return "点赞成功";
        else
            throw new RuntimeException("点赞操作失败");
    }

    /**
     * 保存举报信息
     * @param treeholeId
     * @return
     */
    @Override
    public String sendReport(Integer treeholeId) {
        Integer ret = treeholeMapper.updateReportNum(treeholeId);
        if(ret > 0)
            return "举报成功";
        else
            throw new RuntimeException("举报操作失败");
    }

    /**
     * 返回 文章id 作者id 文章标题 浏览时间 等信息
     * @param userId
     * @return
     */
    @Override
    public List<HistoryVO> getHistory(Integer userId) {
        return treeHistoryMapper.searchHistoryInfo(userId);
    }

    /**
     * 保存历史记录
     * @param userId
     * @param treeholeId
     * @return
     */
    @Override
    public Integer saveHistory(Integer userId, Integer treeholeId) {
        //封装浏览记录 准备插入表中
        TreeHistory record = new TreeHistory();
        record.setTreeholeId(treeholeId);
        record.setTime(new Date());
        record.setUserId(userId);
        //插入数据
        Integer ret = treeHistoryMapper.insertSelective(record);
        if(ret > 0)
            return 1;
        else
            return 0;
    }

}
