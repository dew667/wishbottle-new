package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Message;

import java.util.List;

public interface MessageService {

    //获取管理员警告和通知公告 用于小程序通知页面
     List<Message> getMessage(int userId);

     //读取指定消息
     Message readMessage(Integer id);

     //获取主页滚动通知
     List<Message> getIndexMsg();

     //管理界面获取所有
     List<Message> getAllMsg();

     //删除
     String deleteById(Integer id);

}
