package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.mapper.TreeholeMapper;
import com.hust13.wishbottle.service.TreeholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 搜索所有文章列表
     * @return
     */
    @Override
    public List<Treehole> searchArticleList() {
        return treeholeMapper.searchAllArticle();
    }

    @Override
    public Treehole saveTreeholeArticle(Treehole record) {
        return null;
    }
}
