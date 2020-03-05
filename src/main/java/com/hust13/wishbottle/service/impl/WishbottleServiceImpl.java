package com.hust13.wishbottle.service.impl;


import com.hust13.wishbottle.entity.Pick;
import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.mapper.PickMapper;
import com.hust13.wishbottle.mapper.WishbottleMapper;
import com.hust13.wishbottle.model.vo.WishbottleVO;
import com.hust13.wishbottle.service.ImagFilesService;
import com.hust13.wishbottle.service.WishbottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WishbottleServiceImpl implements WishbottleService {

    @Autowired
    private WishbottleMapper wishbottleMapper;

    @Autowired
    private PickMapper pickMapper;

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

    /**
     * 捡瓶子
     * @param userId
     * @return
     */
    @Override
    public WishbottleVO pickOneBottle(Integer userId) {
        //查询心愿池中是否还有瓶子
        //Integer count = wishbottleMapper.hasBottle(userId);
        //捞取一个瓶子
        WishbottleVO aBottle =  wishbottleMapper.selectOne(userId);
        if(aBottle != null) {
            //更新pick表信息
            Pick record = new Pick();
            record.setPickerId(userId);
            record.setWishbottleId(aBottle.getId());
            record.setPickTime(new Date());
            Integer ret = pickMapper.insertSelective(record);
        }
        //返回瓶子
        return aBottle;
    }

    /**
     * 获取已投放列表
     * @param userId
     * @return
     */
    @Override
    public List<WishbottleVO> getThrowList(Integer userId) {
        return wishbottleMapper.selectThrowList(userId);
    }

    /**
     * 获取草稿列表
     * @param userId
     * @return
     */
    @Override
    public List<Wishbottle> getDraftList(Integer userId) {
        return wishbottleMapper.selectDraftList(userId);
    }

    /**
     * 删除pick表中条目
     * @param id
     * @param userId
     * @return
     */
    @Override
    public String deleteFromPickList(Integer id, Integer userId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("wishbottleId", id);
        map.put("pickerId", userId);
        Integer ret = pickMapper.deleteItem(map);
        if(ret > 0)
            return "删除条目成功";
        else
            throw new RuntimeException("删除条目失败");
    }

    /**
     * 从throw列表删除
     * @param id
     * @return
     */
    @Override
    public String deleteFromThrowList(Integer id) {
        Wishbottle record = new Wishbottle();
        record.setId(id);
        //设置status状态为发布者从自己列表删除
        record.setStatus(2);
        Integer ret = wishbottleMapper.updateByPrimaryKeySelective(record);
        if(ret > 0)
            return "删除条目成功";
        else
            throw new RuntimeException("删除条目失败");
    }

    /**
     * 从draft列表删除
     * @param id
     * @return
     */
    @Override
    public String deleteFromDraftList(Integer id) {
        Wishbottle record = new Wishbottle();
        record.setId(id);
        //设置status状态为发布者从自己列表删除
        record.setStatus(5);
        Integer ret = wishbottleMapper.updateByPrimaryKeySelective(record);
        if(ret > 0)
            return "删除条目成功";
        else
            throw new RuntimeException("删除条目失败");
    }

    /**
     * 读取内容
     * @param id
     * @return
     */
    @Override
    public Wishbottle readOneBottle(Integer id) {
        return wishbottleMapper.readOne(id);
    }

    /**
     * 获取所有瓶子信息
     * @return
     */
    @Override
    public List<Wishbottle> getAllBottle() {
        return wishbottleMapper.getAll();
    }

    /**
     * 管理删除
     * @param id
     * @return
     */
    @Override
    public String deleteById(Integer id) {
        Integer ret = wishbottleMapper.deleteByPrimaryKey(id);
        if(ret > 0)
            return "删除成功";
        else
            throw new RuntimeException("删除失败");
    }

    @Override
    public List<HashMap<Integer, Integer>> activeCount() {
        return wishbottleMapper.activeCount();
    }

}
