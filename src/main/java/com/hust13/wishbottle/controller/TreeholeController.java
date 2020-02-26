package com.hust13.wishbottle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.model.TreeholeArticleVO;
import com.hust13.wishbottle.service.ImagFilesService;
import com.hust13.wishbottle.service.TreeholeService;
import com.hust13.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 树洞控制器
 * update by wzy on 2020/2/21
 */
@RestController
@RequestMapping("/treehole")
public class TreeholeController {

    @Autowired
    private TreeholeService treeholeService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImagFilesService imagFilesService;

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

    /**
     * 获取猜你喜欢列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getRecommend/{pageNum}/{pageSize}")
    public Model getRecommendArticleList(@PathVariable("pageNum") Integer pageNum,
                                         @PathVariable("pageSize") Integer pageSize) {
        Model model = new Model();
        try {
            //排序方式
            String sort = "views desc, reply_num desc, likes desc"; //默认按热度排序-即浏览量、回复量、点赞数
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

    /**
     * 发布树洞文章
     * @param articleData 上传title content 图片文件链接-可多个 语音文件链接
     * @param request
     * @return
     */
    @PostMapping("/releaseArticle")
    public Model releaseArticle(TreeholeArticleVO articleData, HttpServletRequest request) {
        Model model = new Model();
        try {
            //通过openid获取本人userid
            String openid = (String) request.getAttribute("openid");
            Integer writer_id = userService.getUserIdByOpenId(openid);
            //获取文章各项数据
            String title = articleData.getTitle();
            String content = articleData.getContent();
            String[] imagFiles = articleData.getImagFiles();
            String voiceFileUrl = articleData.getVoiceFile();
            //调用服务对图片文件名进行拼接处理
            String imagFilesUrl = imagFilesService.makeImagFilesUrl(imagFiles);
            //封装数据 准备调用服务存储树洞文章记录
            Treehole record = new Treehole();
            record.setWriterId(writer_id);
            record.setTitle(title);
            record.setContent(content);
            record.setPic(imagFilesUrl);
            record.setVoice(voiceFileUrl);
            //调用服务存储树洞文章记录
            Treehole treehole = treeholeService.saveTreeholeArticle(record);
            model.setData(treehole);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("发布失败");
        }
        return model;
    }

    /**
     * 获取单篇文章信息
     * @param treeholeId 文章条目id
     * @return
     */
    @GetMapping("/getOneArticle/{id}")
    public Model getArticle(@PathVariable("id") Integer treeholeId, HttpServletRequest request){
        Model model = new Model();
        try {
            //获取本人id 用于存储历史记录
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId(openid);
            //存储历史记录
            Integer ret = treeholeService.saveHistory(userId, treeholeId);
            //获取文章信息
            model.setData(treeholeService.getOneArticle(treeholeId));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }

    /**
     * 点赞
     * @param treeholeId 树洞文章id
     * @return
     */
    @PostMapping("/giveLike/{id}")
    public Model giveLike(@PathVariable("id") Integer treeholeId) {
        Model model = new Model();
        try {
            model.setData(treeholeService.giveLike(treeholeId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("点赞失败");
        }
        return model;
    }

    /**
     * 举报
     * @param treeholeId 树洞文章id
     * @return
     */
    @PostMapping("/sendReport/{id}")
    public Model sendReport(@PathVariable("id") Integer treeholeId) {
        Model model = new Model();
        try {
            model.setData(treeholeService.sendReport(treeholeId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("举报失败");
        }
        return model;
    }


    /**
     * 分页查询用户浏览记录
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping("/getHistoryList/{pageNum}/{pageSize}")
    public Model getHistoryList(@PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize,
                                HttpServletRequest request) {
        Model model = new Model();
        try {
            //获取用户id
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId(openid);
            //按时间升序排列
            String sort = "h.time asc";
            PageHelper.startPage(pageNum,pageSize,sort);
            PageInfo pageInfo=new PageInfo(treeholeService.getHistory(userId));
            model.setData(pageInfo);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取历史记录失败");
        }
        return model;
    }

}
