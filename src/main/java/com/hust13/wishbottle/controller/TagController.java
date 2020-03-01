package com.hust13.wishbottle.controller;

import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签控制器
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 获取随机的20条标签
     * @return
     */
    @GetMapping("/get")
    public Model getTags() {
        Model model = new Model();
        try{
            model.setData(tagService.getTags());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取标签失败");
        }
        return model;
    }

}
