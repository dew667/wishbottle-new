package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Message;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    //获取通知公告和警告消息
    List<Message> getAllMessage(Integer userId);

    //获取主页滚动通知
    List<Message> getIndexMessage();
}