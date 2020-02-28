package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Pick;

public interface PickMapper {

    //新增一条pick表信息
    int insertSelective(Pick record);

}
