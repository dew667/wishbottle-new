package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Message;

import java.util.List;

public interface MessageService {

    //获取系统消息
    public List<Message> getSysMessage();

    //获取管理员警告，心愿消息，树洞消息
    public List<Message> getMessage(int userId);

    //发布系统消息
    public int releaseSysMessage(Message message);

    //发布警告
    public int releaseWarnMessage(Message message);

    //发布心愿消息
    public int releaseWishbottleMessage(Message message);

    //发布树洞消息
    public int releaseTreeholeMessage(Message message);


}
