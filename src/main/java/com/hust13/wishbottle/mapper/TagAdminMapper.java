package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Tag;
import com.hust13.wishbottle.entity.Wishbottle;

import java.util.List;

public interface TagAdminMapper {

    List<Tag> get20Tags();

    //获取所有
    List<Wishbottle> getAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Tag record);
}