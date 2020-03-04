package com.hust13.wishbottle.controller.weixin;

import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.model.vo.UserVO;
import com.hust13.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * user控制器
 * update by wzy on 2020/2/20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 授权登录接口
     * @param js_code
     * @return
     */
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

    /**
     * 保存用户信息接口
     * @param userInfo
     * @param request
     * @return
     */
    @PostMapping("/save")
    public Model saveUserInfo(@RequestBody User userInfo, HttpServletRequest request) {
        Model model = new Model();
        try{
            //获取用户信息
            String openid = (String) request.getAttribute("openid");
            model.setData(userService.saveUserInfo(userInfo, openid));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("存储用户信息请求失败");
        }
        return model;
    }

    /**
     * 获取用户信息
     * @param request
     * @param userId 如果userId为空则查询本人信息 否则查询指定用户信息
     * @return
     */
    @GetMapping("/getUserInfo")
    public Model getUserInfo(HttpServletRequest request,
                             @RequestParam(value="userId", required=false) Integer userId) {
        Model model = new Model();
        try{
            //如果userId为空则查询本人信息 否则查询指定用户信息
            if(userId == null){
                String openid = (String) request.getAttribute("openid");
                userId =  userService.getUserIdByOpenId(openid);
            }
            model.setData(userService.getUserInfo(userId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("获取用户信息请求失败");
        }
        return model;
    }

    /**
     * 修改用户信息
     * @param record 请求体中包含修改信息 个性签名-标签id数组等
     * @param request
     * @return
     */
    @PutMapping("/update")
    public Model updateUserInfo(HttpServletRequest request, @RequestBody UserVO record) {
        Model model = new Model();
        try{
            //获取本人id
            String openid = (String) request.getAttribute("openid");
            Integer userId =  userService.getUserIdByOpenId(openid);
            record.setId(userId);
            model.setData(userService.updateUserInfo(record));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("修改用户信息请求失败");
        }
        return model;
    }

}
