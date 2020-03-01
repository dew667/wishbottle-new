package com.hust13.wishbottle.controller.weixin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.UserService;
import com.hust13.wishbottle.service.WishbottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/wishbottle")
public class WishbottleController {

    @Autowired
    private WishbottleService wishbottleService;

    @Autowired
    private UserService userService;

    /**
     * 发布心愿瓶
     * @param wishbottle 请求体中应包含 title-标题 content-内容 type-类型
     * @return
     */
    @PostMapping("/throw")
    public Model throwWishbottle(@RequestBody Wishbottle wishbottle, HttpServletRequest request){
        Model model = new Model();
        try {
            //获取心愿瓶发布者id
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId(openid);
            //设置心愿瓶发布者id
            wishbottle.setWriterId(userId);
            //设置心愿瓶抛掷时间
            wishbottle.setThrowTime(new Date());
            //设置心愿瓶状态为可捞取
            wishbottle.setStatus(1);
            //发布心愿瓶并返回该心愿瓶信息
            model.setData(wishbottleService.throwWishbottle(wishbottle));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("发布失败");
        }
        return model;
    }

    /**
     * 保存至草稿箱
     * @param wishbottle 请求体中应包含 title-标题 content-内容 type-类型
     * @param request
     * @return
     */
    @PostMapping("/saveDraft")
    public Model saveDraft(@RequestBody Wishbottle wishbottle, HttpServletRequest request) {
        Model model = new Model();
        try {
            //获取心愿瓶撰写者id
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId(openid);
            //设置心愿瓶撰写者id
            wishbottle.setWriterId(userId);
            //设置草稿修改时间
            wishbottle.setUpdateTime(new Date());
            //设置心愿瓶状态为保存在草稿箱
            wishbottle.setStatus(4);
            //保存心愿瓶并返回该心愿瓶信息
            model.setData(wishbottleService.throwWishbottle(wishbottle));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("保存草稿失败");
        }
        return model;
    }

    /**
     * 捡一个瓶子 通过拦截器限制每日捞取次数
     * @return 若返回data为空 则表明没有可捞取的瓶子
     */
    @GetMapping("/pick")
    public Model pickBottle(HttpServletRequest request) {
        Model model = new Model();
        try {
            //获取拾取人id
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId(openid);
            model.setData(wishbottleService.pickOneBottle(userId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("拾取失败");
        }
        return model;
    }


    /**
     * 分页获取已捞取列表
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping("/getPickList/{pageNum}/{pageSize}")
    public Model getPickList(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             HttpServletRequest request) {
        Model model = new Model();
        try {
            //获取本人id
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId( openid);
            //按捞取时间排序
            String sort = "p.pick_time asc";
            PageHelper.startPage(pageNum,pageSize,sort);
            //根据本人id获取已捞取列表
            PageInfo pageInfo=new PageInfo(wishbottleService.getPickList(userId));
            model.setData(pageInfo);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取已捞取列表失败");
        }
        return model;
    }

    /**
     * 获取已投放列表
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping("/getThrowList/{pageNum}/{pageSize}")
    public Model getThrowList(@PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize,
                              HttpServletRequest request) {
        Model model = new Model();
        try {
            //获取本人id
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId(openid);
            //按捞取时间排序
            String sort = "w.throw_time asc";
            PageHelper.startPage(pageNum,pageSize,sort);
            //根据本人id获取已捞取列表
            PageInfo pageInfo=new PageInfo(wishbottleService.getThrowList(userId));
            model.setData(pageInfo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("获取投放列表失败");
        }
        return model;
    }

    /**
     * 获取草稿列表
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping("/getDraftList/{pageNum}/{pageSize}")
    public Model getDraftList(@PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize,
                              HttpServletRequest request) {
        Model model = new Model();
        try {
            //获取本人id
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId(openid);
            //按捞取时间排序
            String sort = "throw_time asc";
            PageHelper.startPage(pageNum,pageSize,sort);
            //根据本人id获取已捞取列表
            PageInfo pageInfo=new PageInfo(wishbottleService.getDraftList(userId));
            model.setData(pageInfo);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取草稿列表失败");
        }
        return model;
    }

    @GetMapping("/read/{id}")
    public Model readWishbottle(@PathVariable("id") Integer id) {
        Model model = new Model();
        try {
            model.setData(wishbottleService.readOneBottle(id));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("阅读失败");
        }
        return model;
    }

    /**
     * 删除本人pick列表中的条目
     * @param id 瓶子id
     * @param request
     * @return
     */
    @DeleteMapping("/deleteFromPickList/{id}")
    public Model deleteFromPickList(@PathVariable("id") Integer id, HttpServletRequest request) {
        Model model = new Model();
        try {
            //获取本人id
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId(openid);
            model.setData(wishbottleService.deleteFromPickList(id, userId));
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
     * 删除本人throw列表中的条目
     * @param id 瓶子id
     * @return
     */
    @DeleteMapping("/deleteFromThrowList/{id}")
    public Model deleteFromThrowList(@PathVariable("id") Integer id) {
        Model model = new Model();
        try {
            model.setData(wishbottleService.deleteFromThrowList(id));
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
     * 从草稿列表中删除条目
     * @param id 瓶子id
     * @return
     */
    @DeleteMapping("/deleteFromDraftList/{id}")
    public Model deleteFromDraftList(@PathVariable("id") Integer id) {
        Model model = new Model();
        try {
            model.setData(wishbottleService.deleteFromDraftList(id));
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
