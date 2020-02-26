package com.hust13.wishbottle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.User;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.FriendService;
import com.hust13.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 好友-关注控制器
 * created by wzy on 2020/2/19
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

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
            Integer mineId = userService.getUserIdByOpenId(openid);
            model.setData(friendService.concernOneUser(friendId, mineId));
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
            Integer mineId = userService.getUserIdByOpenId(openid);
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
                                @PathVariable("pageSize") Integer pageSize, HttpServletRequest request){
        Model model = new Model();
        try {
            //获取身份标识
            String openid = (String) request.getAttribute("openid");
            Integer mineId = userService.getUserIdByOpenId(openid);
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
    public Model removeConcern(@PathVariable("id") Integer friendId, HttpServletRequest request){
        Model model = new Model();
        try {
            //获取身份标识
            String openid = (String) request.getAttribute("openid");
            Integer mineId = userService.getUserIdByOpenId(openid);
            model.setData(friendService.removeConcernItem(friendId, mineId));
        }catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("删除关注条目时出错");
        }
        return model;
    }

    /**
     * 获取用户关注的数目和粉丝数目
     * @param request
     * @param userId 指定的用户id-可为空表示查询我的信息
     * @return
     */
    @GetMapping("/getConcernNum")
    public Model getConcernNum(HttpServletRequest request,
                               @RequestParam(value="userId", required=false) Integer userId) {
        Model model = new Model();
        try{
            //userId为空则查询本人的信息 否则查询指定用户信息
            if(userId == null) {
                String openid = (String) request.getAttribute("openid");
                userId = userService.getUserIdByOpenId(openid);
            }
            //获取我关注的用户数目
            List<User> iConcern = friendService.searchAllIConcern(userId);
            Integer iConcernNum = iConcern.size();
            //获取我的粉丝数目
            List<User> concernMe = friendService.searchAllConcernMe(userId);
            Integer concernMeNum = concernMe.size();
            //封装数据
            Map<String, Integer> map = new HashMap<>();
            map.put("iConcernNum", iConcernNum);
            map.put("concernMeNum", concernMeNum);
            //设置返回数据
            model.setData(map);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取关注粉丝数目失败");
        }
        return model;
    }
}
