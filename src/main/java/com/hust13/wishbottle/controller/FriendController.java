package com.hust13.wishbottle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.Friend;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
     * @param friend JSON格式包含mineId和friendId
     * @return
     */
    @PostMapping("/concern")
    public Model concernOneUser(@RequestBody Friend friend){
        Model model = new Model();
        try {
            model.setData(friendService.concernOneUser(friend));
        }catch (Exception e)
        {
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
                                @PathVariable("pageSize") Integer pageSize, @RequestParam("id") Integer mineId){
        Model model = new Model();
        try {
            PageHelper.startPage(pageNum,pageSize);
            PageInfo pageInfo=new PageInfo(friendService.searchAllIConcern(mineId));
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
                                @PathVariable("pageSize") Integer pageSize, @RequestParam("id") Integer mineId){
        Model model = new Model();
        try {
            PageHelper.startPage(pageNum,pageSize);
            PageInfo pageInfo=new PageInfo(friendService.searchAllConcernMe(mineId));
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
    public Model removeConcern(@PathVariable("id") Integer friendId){
        Model model = new Model();
        try {
            Friend friend = new Friend();
            Integer mineId = 2;
            friend.setMineId(mineId);
            friend.setFriendId(friendId);
            model.setData(friendService.removeConcernItem(friend));
        }catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("删除关注条目时出错");
        }
        return model;
    }
}
