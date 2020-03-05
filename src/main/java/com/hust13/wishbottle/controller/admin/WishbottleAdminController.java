package com.hust13.wishbottle.controller.admin;

import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.WishbottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 心愿瓶管理控制器
 */
@RestController
@RequestMapping("/admin/wishbottle")
@CrossOrigin(origins = "*")
public class WishbottleAdminController {

    @Autowired
    private WishbottleService wishbottleService;

    /**
     * 获取所有心愿瓶列表
     * @return
     */
    @GetMapping("/getAll")
    public Model getAllBottle() {
        Model model = new Model();
        try {
            model.setData(wishbottleService.getAllBottle());
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
            model.setData(wishbottleService.deleteById(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("删除失败");
        }
        return model;
    }

    /**
     * 统计用户活跃时段
     * @param request
     * @return
     */
    @GetMapping("/active")
    public Model activeCount(HttpServletRequest request) {
        Model model = new Model();
        try{

            model.setData(wishbottleService.activeCount());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("请求失败");
        }
        return model;
    }

}
