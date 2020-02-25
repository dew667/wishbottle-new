package com.hust13.wishbottle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 好友-关注控制器
 * created by wzy on 2020/2/19
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     * 关注某一用户
     * @param friendId 被关注者id
     * @return
     */
    @PostMapping("/concern")
    public Model concernOneUser(@RequestParam("id") Integer friendId, HttpServletRequest request){
        Model model = new Model();
        try {
            //获取身份标识
            String openid = (String) request.getAttribute("openid");
            model.setData(friendService.concernOneUser(friendId, openid));
        }catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("关注用户失败");
        }
        return model;
    }

    /**
     * 分页查询本人关注的用户列表
     * @param pageNum 当前页码
     * @param pageSize 页面大小
     * @return
     */
    @GetMapping("/IConcern/{pageNum}/{pageSize}")
    public Model getAllIConcern(@PathVariable("pageNum") Integer pageNum,
                                @PathVariable("pageSize") Integer pageSize, HttpServletRequest request){
        Model model = new Model();
        try {
            //获取身份标识
            String openid = (String) request.getAttribute("openid");
            PageHelper.startPage(pageNum,pageSize);
            PageInfo pageInfo=new PageInfo(friendService.searchAllIConcern(openid));
            model.setData(pageInfo);
        }catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取本人关注的用户时出错");
        }
        return model;
    }

    /**
     * 查询粉丝
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/ConcernMe/{pageNum}/{pageSize}")
    public Model getAllConcernMe(@PathVariable("pageNum") Integer pageNum,
                                @PathVariable("pageSize") Integer pageSize, HttpServletRequest request){
        Model model = new Model();
        try {
            //获取身份标识
            String openid = (String) request.getAttribute("openid");
            PageHelper.startPage(pageNum,pageSize);
            PageInfo pageInfo=new PageInfo(friendService.searchAllConcernMe(openid));
            model.setData(pageInfo);
        }catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取本人粉丝用户时出错");
        }
        return model;
    }

    /**
     * 删除关注条目
     * @param friendId
     * @return
     */
    @DeleteMapping("/concern/{id}")
    public Model removeConcern(@PathVariable("id") Integer friendId, HttpServletRequest request){
        Model model = new Model();
        try {
            //获取身份标识
            String openid = (String) request.getAttribute("openid");
            model.setData(friendService.removeConcernItem(friendId, openid));
        }catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("删除关注条目时出错");
        }
        return model;
    }
}
