package com.hust13.wishbottle.service.impl;


import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.mapper.WishReplyMapper;
import com.hust13.wishbottle.mapper.WishbottleMapper;
import com.hust13.wishbottle.model.vo.WishbottleVO;
import com.hust13.wishbottle.service.WishbottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 根据本人id查询已捞取列表 返回wishbottleVO封装类
     * @param userId 用户id
     * @return
     */
    @Override
    public List<WishbottleVO> getPickList(Integer userId) {
        return wishbottleMapper.selectPickList(userId);
    }

}
