package com.hust13.wishbottle.controller.weixin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.Message;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.ImagFilesService;
import com.hust13.wishbottle.service.MessageService;
import com.hust13.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 通知消息控制器
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    /**
     * 分页获取通知公告或警告消息
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping("/getAll/{pageNum}/{pageSize}")
    public Model getAllMsg(@PathVariable("pageNum") Integer pageNum,
                           @PathVariable("pageSize") Integer pageSize, HttpServletRequest request) {
        Model model = new Model();
        try {
            //获取本人id
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId(openid);
            //按时间排列
            String sort = "time desc";
            PageHelper.startPage(pageNum,pageSize,sort);
            PageInfo pageInfo=new PageInfo(messageService.getMessage(userId));
            model.setData(pageInfo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("获取通知消息失败");
        }
        return model;
    }

    /**
     * 读取指定消息
     * @param id 消息id
     * @return
     */
    @GetMapping("/read/{id}")
    public Model readMsg(@PathVariable("id") Integer id) {
        Model model = new Model();
        try{
            model.setData(messageService.readMessage(id));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("读取失败");
        }
        return model;
    }

    /**
     * 获取主页滚动通知
     * @return
     */
    @GetMapping("/getIndexMsg")
    public Model getIndexMsg() {
        Model model = new Model();
        try{
            model.setData(messageService.getIndexMsg());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("读取失败");
        }
        return model;
    }
}
