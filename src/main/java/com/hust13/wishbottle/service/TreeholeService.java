package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.model.HistoryData;

import java.util.List;
import java.util.Map;

/**
 * 树洞文章Service接口
 * update by wzy on 2020/2/21
 */
public interface TreeholeService {

    List<Treehole> searchArticleList();

    Treehole saveTreeholeArticle(Treehole record);

    Treehole getOneArticle(Integer treeholeId);

    String giveLike(Integer treeholeId);

    String sendReport(Integer treeholeId);

    List<HistoryData> getHistory(Integer userId);

    Integer saveHistory(Integer userId, Integer treeholeId);

}
