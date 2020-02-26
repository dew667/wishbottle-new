package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.Friend;
import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.mapper.FriendMapper;
import com.hust13.wishbottle.mapper.UserMapper;
import com.hust13.wishbottle.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param friendId
     * @return
     */
    @Override
    public Friend concernOneUser(Integer friendId, Integer mineId) {
        Friend record = new Friend();
        record.setFriendId(friendId);
        record.setMineId(mineId);
        Integer ret = friendMapper.insertSelective(record);
        //成功插入条目
        if(ret > 0)
            return record;
        else
            throw new RuntimeException("向数据库插入关注条目失败");
    }

    /**
     * 查询所有本人关注的用户
     * @param mineId
     * @return
     */
    @Override
    public List<User> searchAllIConcern(Integer mineId) {
        return friendMapper.searchAllIConcern(mineId);
    }

    /**
     * 查询我的所有粉丝
     * @param mineId
     * @return
     */
    @Override
    public List<User> searchAllConcernMe(Integer mineId) {
        return friendMapper.searchAllConcernMe(mineId);
    }

    /**
     * 删除关注条目
     * @return
     */
    @Override
    public Integer removeConcernItem(Integer friendId, Integer mineId) {
        Friend record = new Friend();
        record.setMineId(mineId);
        record.setFriendId(friendId);
        Integer ret = friendMapper.deleteByFriendItem(record);
        return ret;
    }
}
