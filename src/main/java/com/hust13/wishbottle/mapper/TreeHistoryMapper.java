package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.TreeHistory;
import com.hust13.wishbottle.model.vo.HistoryVO;

import java.util.List;

public interface TreeHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TreeHistory record);

    int insertSelective(TreeHistory record);

    TreeHistory selectByPrimaryKey(Integer id);

    //通过用户id查询浏览记录 返回 文章id-作者id-文章标题-浏览时间 组成的List
    List<HistoryVO> searchHistoryInfo(Integer userId);

    int updateByPrimaryKeySelective(TreeHistory record);

    int updateByPrimaryKey(TreeHistory record);

    //查表 看是否已存在该项记录
    Integer findIfExist(Integer treeholeId, Integer userId);

    //更新记录的时间
    Integer updateHistoryDate(TreeHistory record);
}