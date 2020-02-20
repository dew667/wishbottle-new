package com.hust13.wishbottle.controller;

import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * user控制器
 * update by wzy on 2020/2/20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Model login(@RequestParam("js_code") String js_code){
        Model model = new Model();
        try{
            model.setData(userService.userLogin(js_code));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("登录请求失败");
        }
        return model;
    }

    @PostMapping("/save")
    public Model saveUserInfo(@RequestBody User userInfo, HttpServletRequest request) {
        //获取身份标识
        String openid = (String) request.getAttribute("openid");
        Model model = new Model();
        try{
            model.setData(userService.saveUserInfo(userInfo, openid));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("存储用户信息请求失败");
        }
        return model;
    }
}
