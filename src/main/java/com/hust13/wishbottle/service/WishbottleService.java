package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.model.vo.WishbottleVO;

import java.util.HashMap;
import java.util.List;

public interface WishbottleService {

    //发布或保存心愿瓶
    Wishbottle throwWishbottle(Wishbottle wishbottle);

    //根据本人id查询已捞取列表 返回wishbottle封装类
    List<WishbottleVO> getPickList(Integer userId);

    //捡一个瓶子
    WishbottleVO pickOneBottle(Integer userId);

    //根据本人id查询已投放列表 返回wishbottle封装类
    List<WishbottleVO> getThrowList(Integer userId);

    //获取草稿列表
    List<Wishbottle> getDraftList(Integer userId);

    //从pick列表中删除条目
    String deleteFromPickList(Integer id, Integer userId);

    //从throw列表中删除条目
    String deleteFromThrowList(Integer id);

    //从draft列表删除条目
    String deleteFromDraftList(Integer id);

    //阅读瓶子中信息
    Wishbottle readOneBottle(Integer id);

    //获取所有心愿瓶
    List<Wishbottle> getAllBottle();

    //管理员删除
    String deleteById(Integer id);

    //统计用户活跃时段
    List<HashMap<Integer,Integer>> activeCount();
}
