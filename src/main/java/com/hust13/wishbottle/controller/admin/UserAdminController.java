package com.hust13.wishbottle.controller.admin;

import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理控制器
 */
@RestController
@RequestMapping("/admin/user")
@CrossOrigin(origins = "*")
public class UserAdminController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有列表
     * @return
     */
    @GetMapping("/getAll")
    public Model getAllUser() {
        Model model = new Model();
        try {
            model.setData(userService.getAllUser());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }

    @RequestMapping("/open/{id}")
    public Model openOne(@PathVariable("id") Integer id) {
        Model model = new Model();
        try {
            model.setData(userService.openById(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("解封失败");
        }
        return model;
    }

    @RequestMapping("/close/{id}")
    public Model deleteOne(@PathVariable("id") Integer id) {
        Model model = new Model();
        try {
            model.setData(userService.closeById(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("封禁失败");
        }
        return model;
    }

    /**
     * 统计用户性别
     * @param request
     * @return
     */
    @GetMapping("/gender")
    public Model genderCount(HttpServletRequest request) {
        Model model = new Model();
        try{

            model.setData(userService.genderCount());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("请求失败");
        }
        return model;
    }

    /**
     * 统计用户年龄
     * @param request
     * @return
     */
    @GetMapping("/age")
    public Model ageCount(HttpServletRequest request) {
        Model model = new Model();
        try{

            model.setData(userService.ageCount());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("请求失败");
        }
        return model;
    }

    /**
     * 统计用户所属城市
     * @param request
     * @return
     */
    @GetMapping("/city")
    public Model cityCount(HttpServletRequest request) {
        Model model = new Model();
        try{

            model.setData(userService.cityCount());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("请求失败");
        }
        return model;
    }

}
