package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.WishReply;
import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.mapper.WishReplyMapper;
import com.hust13.wishbottle.mapper.WishbottleMapper;
import com.hust13.wishbottle.service.WishbottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class WishbottleServiceImpl implements WishbottleService {

    @Autowired
    WishbottleMapper wishbottleMapper;

    @Autowired
    WishReplyMapper wishReplyMapper;

    @Override
    public Wishbottle searchWishbottle() {
        return null;
    }

    @Override
    public int throwWishbottle(Wishbottle wishbottle) {
        return 0;
    }

    @Override
    public List<Wishbottle> getWishbottle(int userId) {
        return null;
    }

    @Override
    public int writeWishReply(WishReply wishReply) {
        return 0;
    }
}
