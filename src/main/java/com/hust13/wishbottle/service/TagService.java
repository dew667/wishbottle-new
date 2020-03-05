package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Tag;
import com.hust13.wishbottle.entity.Wishbottle;

import java.util.List;

public interface TagService {

    List<Tag> getTags();

    //获取所有标签
    List<Wishbottle> getAllTag();

    //管理员删除
    String deleteById(Integer id);

    //添加标签
    public int addTag(Tag tag);

}
