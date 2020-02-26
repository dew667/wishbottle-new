package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.WishReply;
import com.hust13.wishbottle.entity.Wishbottle;

import java.util.List;

public interface WishbottleService {

    //发布心愿瓶
    Wishbottle throwWishbottle(Wishbottle wishbottle);

}
