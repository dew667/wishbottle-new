package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.IndexPic;

import java.util.List;

/**
 * 轮播图业务接口
 */
public interface IndexPicService {

    //获取所有轮播图
    List<IndexPic> getAll();

}
