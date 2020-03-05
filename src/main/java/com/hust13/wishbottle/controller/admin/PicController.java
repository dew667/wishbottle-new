package com.hust13.wishbottle.controller.admin;

import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.IndexPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 *  轮播图控制器
 */
@RestController
@RequestMapping("/admin/pic")
@CrossOrigin(origins = "*")
public class PicController {

    @Autowired
    IndexPicService indexPicService;

    @RequestMapping("/up")
    @ResponseBody
    public Model uploadfile(@RequestParam("file") MultipartFile file) {
        Model model = new Model();
        try {
            model.setData(indexPicService.insert(file));
        }catch (Exception e){
            model.setCode(1);
            model.setMsg("添加失败");
        }

        return model;
    }

}
