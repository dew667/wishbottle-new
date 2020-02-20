package com.hust13.wishbottle.controller;

import com.hust13.wishbottle.entity.WishReply;
import com.hust13.wishbottle.entity.Wishbottle;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.WishbottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishbottle")
public class WishbottleController {

    @Autowired
    WishbottleService wishbottleService;

    /**
     * 捞取心愿瓶
     * @return
     */
    @RequestMapping("/search")
    public Model searchWishbottle(){
        Model model = new Model();
        try {
            model.setData(wishbottleService.searchWishbottle());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("捞取失败");
        }
        return model;
    }

    /**
     * 发布心愿瓶
     * @param wishbottle
     * @return
     */
    @RequestMapping("/throw")
    public Model throwWishbottle(Wishbottle wishbottle){
        Model model = new Model();
        try {
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
     * 查看自己的心愿瓶
     * @param userId
     * @return
     */
    @RequestMapping("/getall")
    public Model getWishbottle(int userId){
        Model model = new Model();
        try {
            model.setData(wishbottleService.getWishbottle(userId));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("查看失败");
        }
        return model;
    }

    /**
     * 填写心愿回复
     * @param wishReply
     * @return
     */
    @RequestMapping("/writewishreply")
    public Model writeWishReply(WishReply wishReply){
        Model model = new Model();
        try {
            model.setData(wishbottleService.writeWishReply(wishReply));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("填写失败");
        }
        return model;
    }

}
