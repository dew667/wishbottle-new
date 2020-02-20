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
    MessageMapper messageMapper;

    @Override
    public List<Message> getSysMessage() {
        return null;
    }

    @Override
    public List<Message> getMessage(int userId){
        return null;
    }

    @Override
    public int releaseSysMessage(Message message) {
        return 0;
    }

    @Override
    public int releaseWarnMessage(Message message) {
        return 0;
    }

    @Override
    public int releaseWishbottleMessage(Message message) {
        return 0;
    }

    @Override
    public int releaseTreeholeMessage(Message message) {
        return 0;
    }
}
