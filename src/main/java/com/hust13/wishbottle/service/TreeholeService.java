package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.model.vo.HistoryVO;
import com.hust13.wishbottle.model.vo.TreeholeVO;

import java.util.List;

/**
 * 树洞文章Service接口
 * update by wzy on 2020/2/21
 */
public interface TreeholeService {

    List<TreeholeVO> searchArticleList();

    Treehole saveTreeholeArticle(Treehole record);

    TreeholeVO getOneArticle(Integer treeholeId);

    String giveLike(Integer treeholeId);

    String sendReport(Integer treeholeId);

    List<HistoryVO> getHistory(Integer userId);

    Integer saveHistory(Integer userId, Integer treeholeId);

    List<Treehole> getAllTreehole();

    String deleteById(Integer id);

}
