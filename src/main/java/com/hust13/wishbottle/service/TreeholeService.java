package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Treehole;

import java.util.List;

/**
 * 树洞文章Service接口
 * update by wzy on 2020/2/21
 */
public interface TreeholeService {

    List<Treehole> searchArticleList();

    Treehole saveTreeholeArticle(Treehole record);

}
