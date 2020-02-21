package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Treehole;

import java.util.List;

public interface TreeholeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Treehole record);

    int insertSelective(Treehole record);

    Treehole selectByPrimaryKey(Integer id);

    //搜索所有文章列表
    List<Treehole> searchAllArticle();

    int updateByPrimaryKeySelective(Treehole record);

    int updateByPrimaryKey(Treehole record);
}