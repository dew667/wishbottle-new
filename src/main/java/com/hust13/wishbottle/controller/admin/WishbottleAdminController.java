package com.hust13.wishbottle.controller.admin;

import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.WishbottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 心愿瓶管理控制器
 */
@RestController
@RequestMapping("/admin/wishbottle")
public class WishbottleAdminController {

    @Autowired
    private WishbottleService wishbottleService;

    @GetMapping("/getAll")
    public Model getAllBottle() {
        Model model = new Model();
        try {
            model.setData(null);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }

}
