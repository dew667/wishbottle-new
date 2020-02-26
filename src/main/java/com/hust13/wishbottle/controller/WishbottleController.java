package com.hust13.wishbottle.controller;

import com.hust13.wishbottle.entity.WishReply;
import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.UserService;
import com.hust13.wishbottle.service.WishbottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            //设置心愿瓶状态为漂浮在海中
            wishbottle.setStatus(0);
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
            model.setData(null);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("保存草稿失败");
        }
        return model;
    }

}
