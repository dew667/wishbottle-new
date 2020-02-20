package com.hust13.wishbottle.controller;

import com.hust13.wishbottle.entity.Manager;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    /**
     * 判断删除管理员的账户是否为超级管理员
     * @param managerId
     * @return
     */
    @RequestMapping("/judge")
    public Model judgeManager(int managerId){
        Model model = new Model();
        try {
            model.setData(managerService.judgeManager(managerId));
        }catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("判断失败");
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
     * 删除管理员账户
     * @param managerId
     * @return
     */
        @RequestMapping("/delete")
        public Model deleteManager(int managerId){
            Model model = new Model();
            try{
                model.setData(managerService.deleteManager(managerId));
            }
            catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("删除管理员失败");
        }
        return model;
    }

    /**
     * 获取所有管理员账户
     * @return
     */
    @RequestMapping("/getall")
    public Model getManagers(){
        Model model = new Model();
        try{
            model.setData(managerService.getManagers());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }
}
