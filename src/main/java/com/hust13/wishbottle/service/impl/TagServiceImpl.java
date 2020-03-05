package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.Tag;
import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.mapper.TagAdminMapper;
import com.hust13.wishbottle.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * tag业务实现类
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagAdminMapper tagAdminMapper;

    /**
     * 获取20条随机的标签
     * @return
     */
    @Override
    public List<Tag> getTags() {
        return tagAdminMapper.get20Tags();
    }

    @Override
    public List<Wishbottle> getAllTag() {
        return tagAdminMapper.getAll();
    }

    @Override
    public String deleteById(Integer id) {
        Integer ret = tagAdminMapper.deleteByPrimaryKey(id);
        if(ret > 0)
            return "删除成功";
        else
            throw new RuntimeException("删除失败");
    }

    @Override
    public int addTag(Tag tag) {
        tagAdminMapper.insert(tag);
        return 1;
    }
}
