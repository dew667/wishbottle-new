package com.hust13.wishbottle.controller.admin;

import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  消息管理控制器
 */
@RestController
@RequestMapping("/admin/message")
public class MessageAdminController {

    @Autowired
    private MessageService messageService;

    /**
     * 获取所有树洞列表
     * @return
     */
    @GetMapping("/getAll")
    public Model getAllMsg() {
        Model model = new Model();
        try {
            model.setData(messageService.getAllMsg());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }

    @DeleteMapping("/delete/{id}")
    public Model deleteOne(@PathVariable("id") Integer id) {
        Model model = new Model();
        try {
            model.setData(messageService.deleteById(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("删除失败");
        }
        return model;
    }

}