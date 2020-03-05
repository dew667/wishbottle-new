package com.hust13.wishbottle.controller.admin;

import com.hust13.wishbottle.entity.Tag;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  标签管理控制器
 */
@RestController
@RequestMapping("/admin/tag")
@CrossOrigin(origins = "*")
public class TagAdminController {

    @Autowired
    TagService tagService;

    /**
     * 获取所有标签列表
     * @return
     */
    @GetMapping("/getAll")
    public Model getAllTag() {
        Model model = new Model();
        try {
            model.setData(tagService.getAllTag());
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
            model.setData(tagService.deleteById(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("删除失败");
        }
        return model;
    }

    @RequestMapping("/add")
    public Model addTag(Tag tag){
        Model model = new Model();
        try{
            model.setData(tagService.addTag(tag));
        }catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("添加标签失败");
        }
        return model;
    }
}
