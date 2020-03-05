package com.hust13.wishbottle.controller.admin;

import com.hust13.wishbottle.entity.Manager;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  管理员管理控制器
 */
@RestController
@RequestMapping("/admin/manager")
@CrossOrigin(origins = "*")
public class ManagerAdminController {

    @Autowired
    private ManagerService managerService;

    /**
     * 判断删除管理员的账户是否为超级管理员
     * @param id
     * @return
     */
    @RequestMapping("/judge")
    public Model judgeManager(int id){
        Model model = new Model();
        try {
            model.setData(managerService.judgeManager(id));
        }catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("判断失败");
        }
        return model;
    }

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

    /**
     * 添加管理员账户
     * @param manager
     * @return
     */
    @RequestMapping("/add")
    public Model addManager(Manager manager){
        Model model = new Model();
        try{
            model.setData(managerService.addManager(manager));
        }catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("添加管理员失败");
        }
        return model;
    }

    /**
     * 管理员登陆
     * @param account
     * @param psd
     * @return
     */
    @RequestMapping("/login")
    public Model login(@RequestParam("username") String account,
                       @RequestParam("password") String psd){
        Model model = new Model();
        try{
            model.setData(managerService.managerLogin(account,psd));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("登录请求失败");
        }
        return model;
    }
}
