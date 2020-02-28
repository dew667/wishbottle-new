package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.model.vo.WishbottleVO;

import java.util.List;

public interface WishbottleService {

    //发布或保存心愿瓶
    Wishbottle throwWishbottle(Wishbottle wishbottle);

    //根据本人id查询已捞取列表 返回wishbottle封装类
    List<WishbottleVO> getPickList(Integer userId);

    //捡一个瓶子
    Wishbottle pickOneBottle(Integer userId);

}
