package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.Friend;
import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.mapper.FriendMapper;
import com.hust13.wishbottle.model.vo.ConcernVO;
import com.hust13.wishbottle.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 好友-关注DAO实现类
 * created by wzy on 2020/2/19
 */
@Service
@Transactional //开启事务
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

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
        //判断是否已存在对应项
        Map<String, Integer> map = new HashMap<>();
        map.put("mineId", mineId);
        map.put("friendId", friendId);
        Integer count = friendMapper.ifExist(map);
        //插入条目
        Integer ret = 0;
        if(count == 0)
            ret = friendMapper.insertSelective(record);
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
    public List<ConcernVO> searchAllIConcern(Integer mineId) {
        return friendMapper.searchAllIConcern(mineId);
    }

    /**
     * 查询我的所有粉丝
     * @param mineId
     * @return
     */
    @Override
    public List<ConcernVO> searchAllConcernMe(Integer mineId) {
        return friendMapper.searchAllConcernMe(mineId);
    }

    /**
     * 删除关注条目
     * @return
     */
    @Override
    public String removeConcernItem(Integer friendId, Integer mineId) {
        Friend record = new Friend();
        record.setMineId(mineId);
        record.setFriendId(friendId);
        Integer ret = friendMapper.deleteByFriendItem(record);
        if(ret > 0)
            return "删除成功";
        else
            throw new RuntimeException("删除失败");
    }

    /**
     * 是否关注
     * @param mineId
     * @param userId
     * @return
     */
    @Override
    public Map<String, Integer> isConcernOneUser(Integer mineId, Integer userId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("mineId", mineId);
        map.put("userId", userId);
        Integer ret = friendMapper.isConcernOneUser(map);
        //返回结果
        Map<String, Integer> isConcernMap = new HashMap<>();
        isConcernMap.put("isConcern", ret);
        return isConcernMap;
    }

    @Override
    public List<User> getRecommend(Integer mineId) {
        List<User> users = friendMapper.getRecommend(mineId);
        //若推荐用户为空则随机推荐
        if(users.size() <= 0)
            users = friendMapper.getRandUsers(mineId);
        return users;
    }
}
