package com.hust13.wishbottle.controller;

import com.hust13.wishbottle.entity.Message;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    /**
     * 获取系统消息
     * @return
     */
    @RequestMapping("/getsys")
    public Model getSysMessage(){
        Model model = new Model();
        try {
            model.setData(messageService.getSysMessage());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }



    /**
     * 获取管理员警告，心愿消息，树洞消息
     * @param userId
     * @return
     */
    @RequestMapping("/getmessage")
    public Model getWarnMessage(int userId){
        Model model = new Model();
        try {
            model.setData(messageService.getMessage(userId));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }

    /**
     * 发布系统消息
     * @param message
     * @return
     */
    @RequestMapping("/releasesys")
    public Model releaseSysMessage(Message message){
        Model model = new Model();
        try {
            model.setData(messageService.releaseSysMessage(message));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("发布失败");
        }
        return model;
    }

    /**
     * 发布警告
     * @param message
     * @return
     */
    @RequestMapping("/releasewarn")
    public Model releaseWarnMessage(Message message){
        Model model = new Model();
        try {
            model.setData(messageService.releaseWarnMessage(message));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("发布失败");
        }
        return model;
    }

    /**
     * 发布心愿消息
     * @param message
     * @return
     */
    @RequestMapping("/releasewishbottle")
    public Model releaseWishbottleMessage(Message message){
        Model model = new Model();
        try {
            model.setData(messageService.releaseWishbottleMessage(message));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("发布失败");
        }
        return model;
    }

    /**
     * 发布树洞消息
     * @param message
     * @return
     */
    @RequestMapping("/releaseTreehole")
    public Model releaseTreeholeMessage(Message message){
        Model model = new Model();
        try {
            model.setData(messageService.releaseTreeholeMessage(message));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("发布失败");
        }
        return model;
    }
}
