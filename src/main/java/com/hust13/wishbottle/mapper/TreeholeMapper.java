package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.model.vo.TreeholeVO;

import java.util.List;

public interface TreeholeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Treehole record);

    int insertSelective(Treehole record);

    Treehole selectByPrimaryKey(Integer id);

    //搜索所有文章列表
    List<TreeholeVO> searchAllArticle();

    int updateByPrimaryKeySelective(Treehole record);

    int updateByPrimaryKey(Treehole record);

    //获取单篇文章信息
    TreeholeVO getArticleInfo(Integer id);

    //更新文章浏览量
    Integer updateArticleViews(Integer id);

    //更新文章评论回复量
    Integer updateCommentNum(Integer id);

    //更新文章点赞数量
    Integer updateLikesNum(Integer id);

    //更新文章举报数码
    Integer updateReportNum(Integer id);
}