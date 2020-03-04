package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.model.vo.WishbottleVO;

import java.util.List;

public interface WishbottleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Wishbottle record);

    int insertSelective(Wishbottle record);

    Wishbottle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Wishbottle record);

    int updateByPrimaryKey(Wishbottle record);

    //根据用户id查询已捞取心愿瓶的信息
    List<WishbottleVO> selectPickList(Integer userId);

    //获取一条心愿瓶信息
    WishbottleVO selectOne(Integer userId);

    //判断是否还剩有未捞的瓶子
    Integer hasBottle(Integer userId);

    List<WishbottleVO> selectThrowList(Integer userId);

    List<Wishbottle> selectDraftList(Integer userId);

    //读取bottle中信息
    Wishbottle readOne(Integer id);

    //获取所有
    List<Wishbottle> getAll();
}