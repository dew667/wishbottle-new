package com.hust13.wishbottle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.model.TreeholeArticleVO;
import com.hust13.wishbottle.service.TreeholeService;
import com.hust13.wishbottle.service.UploadFileService;
import com.hust13.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 树洞控制器
 * update by wzy on 2020/2/21
 */
@RestController
@RequestMapping("/treehole")
public class TreeholeController {

    @Autowired
    TreeholeService treeholeService;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    UserService userService;

    /**
     * 分页查询获取所有树洞文章 按时间或热度排序
     * @param pageNum
     * @param pageSize
     * @param orderBy 排序方式 time-时间 hot-热度
     * @return
     */
    @GetMapping("/getArticleList/{pageNum}/{pageSize}")
    public Model getArticleList(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize,
                                @RequestParam(value="orderBy", required=false) String orderBy){
        Model model = new Model();
        try {
            //排序方式
            String sort = "views desc, reply_num desc, likes desc"; //默认按热度排序-即浏览量、回复量、点赞数
            if("hot".equals(orderBy)){ //按热度排序
                sort = "views desc, reply_num desc, likes desc";
            } else if("time".equals(orderBy)){ //按时间排序
                sort = "time desc";
            }
            PageHelper.startPage(pageNum,pageSize,sort);
            PageInfo pageInfo=new PageInfo(treeholeService.searchArticleList());
            model.setData(pageInfo);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }

    @PostMapping("/releaseArticle")
    public Model releaseArticle(TreeholeArticleVO articleData, HttpServletRequest request) {
        Model model = new Model();
        try {
            String openid = (String) request.getAttribute("openid");
            Integer writer_id = userService.getUserIdByOpenId(openid);
            String title = articleData.getTitle();
            String content = articleData.getContent();
            MultipartFile[] imagFiles = articleData.getImagFiles();
            MultipartFile voiceFile = articleData.getVoiceFile();
            HashMap<String, String> filePathMap = uploadFileService.uploadFiles(imagFiles, voiceFile);
            String imagFilesPath = filePathMap.get("imagFiles");
            String voiceFilePath = filePathMap.get("voiceFile");
            Treehole record = new Treehole();
            record.setWriterId(writer_id);
            model.setData(null);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }

}
