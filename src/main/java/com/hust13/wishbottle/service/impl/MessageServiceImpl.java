package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.Message;
import com.hust13.wishbottle.mapper.MessageMapper;
import com.hust13.wishbottle.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 获取通知公告和警告
     * @param userId
     * @return
     */
    @Override
    public List<Message> getMessage(int userId){
        List<Message> msgList = messageMapper.getAllMessage(userId);
        return msgList;
    }

    /**
     * 阅读指定消息
     * @param id
     * @return
     */
    @Override
    public Message readMessage(Integer id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取主页滚动通知
     * @return
     */
    @Override
    public List<Message> getIndexMsg() {
        return messageMapper.getIndexMessage();
    }

}
