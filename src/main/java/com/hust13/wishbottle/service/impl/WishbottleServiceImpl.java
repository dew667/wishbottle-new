package com.hust13.wishbottle.service.impl;


import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.mapper.WishReplyMapper;
import com.hust13.wishbottle.mapper.WishbottleMapper;
import com.hust13.wishbottle.service.WishbottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WishbottleServiceImpl implements WishbottleService {

    @Autowired
    private WishbottleMapper wishbottleMapper;

    @Autowired
    private WishReplyMapper wishReplyMapper;

    /**
     * 抛掷心愿瓶
     * @param wishbottle
     * @return
     */
    @Override
    public Wishbottle throwWishbottle(Wishbottle wishbottle) {
        Integer ret = wishbottleMapper.insertSelective(wishbottle);
        return wishbottle;
    }

}
