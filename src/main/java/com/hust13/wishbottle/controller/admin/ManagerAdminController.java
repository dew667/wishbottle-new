package com.hust13.wishbottle.controller.admin;

import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  管理员管理控制器
 */
@RestController
@RequestMapping("/admin/manager")
public class ManagerAdminController {

    @Autowired
    private ManagerService managerService;

    /**
     * 获取所有
     * @return
     */
    @GetMapping("/getAll")
    public Model getAllMsg() {
        Model model = new Model();
        try {
            model.setData(managerService.getAllManager());
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
            model.setData(managerService.deleteById(id));
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
