package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.IndexPic;
import com.hust13.wishbottle.mapper.IndexPicMapper;
import com.hust13.wishbottle.service.IndexPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 轮播图业务实现类
 */
@Service
@Transactional
public class IndexPicServiceImpl implements IndexPicService {

    @Autowired
    private IndexPicMapper indexPicMapper;

    /**
     * 获取所有轮播图
     * @return
     */
    @Override
    public List<IndexPic> getAll() {
        return indexPicMapper.getAll();
    }
}
