package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.Friend;
import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.mapper.FriendMapper;
import com.hust13.wishbottle.mapper.UserMapper;
import com.hust13.wishbottle.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 好友-关注DAO实现类
 * created by wzy on 2020/2/19
 */
@Service
@Transactional //开启事务
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 关注某一用户
     * @param friend
     * @return
     */
    @Caching(
            //使缓存失效，避免脏数据
            evict = {
                    @CacheEvict(value="concern", key="'concern-list'+#friend.mineId"),
                    @CacheEvict(value="concern", key="'friend-list'+#friend.friendId")
            }
    )
    @Override
    public Friend concernOneUser(Friend friend) {
        Integer ret = friendMapper.insertSelective(friend);
        //成功插入条目
        if(ret > 0)
            return friend;
        else
            throw new RuntimeException("向数据库插入关注条目失败");
    }

    /**
     * 查询所有本人关注的用户
     * @param mineId
     * @return
     */
    @Cacheable(value="concern", key="'concern-list'+#mineId") //查询和保存redis缓存
    @Override
    public List<User> searchAllIConcern(Integer mineId) {
        return friendMapper.searchAllIConcern(mineId);
    }

    /**
     * 查询我的所有粉丝
     * @param mineId
     * @return
     */
    @Cacheable(value="concern", key="'friend-list'+#mineId") //查询和保存redis缓存
    @Override
    public List<User> searchAllConcernMe(Integer mineId) {
        return friendMapper.searchAllConcernMe(mineId);
    }

    /**
     * 删除关注条目
     * @return
     */
    @Caching(
            //使缓存失效，避免脏数据
            evict = {
                    @CacheEvict(value="concern", key="'concern-list'+#record.mineId"),
                    @CacheEvict(value="concern", key="'friend-list'+#record.friendId")
            }
    )
    @Override
    public Integer removeConcernItem(Friend record) {
        Integer ret = friendMapper.deleteByFriendItem(record);
        return ret;
    }
}
