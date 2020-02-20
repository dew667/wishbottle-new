package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.WishReply;
import com.hust13.wishbottle.entity.Wishbottle;

import java.util.List;

public interface WishbottleService {

    //捞心愿瓶
    public Wishbottle searchWishbottle();

    //发布心愿瓶
    public int throwWishbottle(Wishbottle wishbottle);

    //查看自己的心愿瓶
    public List<Wishbottle> getWishbottle(int userId);

    //写心愿瓶回复
    public int writeWishReply(WishReply wishReply);

}
