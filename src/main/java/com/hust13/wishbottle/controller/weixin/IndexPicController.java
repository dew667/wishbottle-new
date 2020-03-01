package com.hust13.wishbottle.controller.weixin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.IndexPic;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.IndexPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 轮播图控制器
 */
@RestController
@RequestMapping("/index")
public class IndexPicController {

    @Autowired
    private IndexPicService indexPicService;

    /**
     * 分页获取轮播图
     * @return
     */
    @GetMapping("/get/{pageNum}/{pageSize}")
    public Model getIndexPic(@PathVariable("pageNum")Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        Model model = new Model();
        try{
            String sort = "time desc";
            PageHelper.startPage(pageNum,pageSize,sort);
            PageInfo pageInfo=new PageInfo(indexPicService.getAll());
            model.setData(pageInfo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }

}
