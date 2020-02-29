package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Pick;

import java.util.Map;

public interface PickMapper {

    //新增一条pick表信息
    int insertSelective(Pick record);

    //删除条目
    int deleteItem(Map<String, Integer> map);

}
