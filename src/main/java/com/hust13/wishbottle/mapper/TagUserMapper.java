package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Tag;

import java.util.List;
import java.util.Map;

public interface TagUserMapper {

    List<Tag> getTagsByUserId(Integer userId);

    Integer insertRecord(Map<String, Integer> map);

    Integer deleteAllByUserId(Integer userId);

}
