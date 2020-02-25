package com.hust13.wishbottle.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.TreeReplyService;
import com.hust13.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 树洞评论回复控制器
 * update by wzy on 2020/2/21
 */
@RestController
@RequestMapping("/treeReply")
public class TreeReplyController {

    @Autowired
    private TreeReplyService treeReplyService;

    @Autowired
    private UserService userService;

    /**
     * 获取树洞文章下面的所有评论
     * @param treeholeId 树洞文章的id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getAllComments/{pageNum}/{pageSize}")
    public Model getAllComments(@RequestParam("id") Integer treeholeId,
                                @PathVariable("pageNum") Integer pageNum,
                                @PathVariable("pageSize") Integer pageSize){
        Model  model = new Model();
        try {
            //按时间升序排列
            String sort = "time asc";
            PageHelper.startPage(pageNum,pageSize,sort);
            PageInfo pageInfo=new PageInfo(treeReplyService.getAllComments(treeholeId));
            model.setData(pageInfo);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取评论失败");
        }
        return model;
    }

    /**
     * 获取作者的评论
     * @param treeholeId 树洞文章id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getCommentsOfAuthor/{pageNum}/{pageSize}")
    public Model getCommentsOfAuthor(@RequestParam("id") Integer treeholeId,
                                        @PathVariable("pageNum") Integer pageNum,
                                        @PathVariable("pageSize") Integer pageSize) {
        Model  model = new Model();
        try {
            //按时间升序排列
            String sort = "time asc";
            PageHelper.startPage(pageNum,pageSize,sort);
            PageInfo pageInfo=new PageInfo(treeReplyService.getAllCommentsOfAuthor(treeholeId));
            model.setData(pageInfo);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取评论失败");
        }
        return model;
    }

    /**
     * 获取指定评论下面的所有回复
     * @param commentId 指定评论的id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getAllReplies/{pageNum}/{pageSize}")
    public Model getAllReplys(@RequestParam("id") Integer commentId,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize){
        Model  model = new Model();
        try {
            //按时间升序排列
            String sort = "time asc";
            PageHelper.startPage(pageNum,pageSize,sort);
            PageInfo pageInfo=new PageInfo(treeReplyService.getAllReplies(commentId));
            model.setData(pageInfo);
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取评论失败");
        }
        return model;
    }

    /**
     * 发表评论
     * @param record 请求体中应包含 treeholeId-文章id content-内容
     * @return
     */
    @PostMapping("/releaseComment")
    public Model releaseComment(@RequestBody TreeReply record, HttpServletRequest request) {
        Model model = new Model();
        try{
            String openid = (String) request.getAttribute("openid");
            Integer replyerId = userService.getUserIdByOpenId(openid);
            record.setReplyerId(replyerId);
            //获取时间
            Date date = new Date();
            record.setTime(date);
            //设置answerId为0 表示评论指向树洞文章
            record.setAnswerId(0);
            //初始化其他数据
            record.setStatus(1);
            record.setlikes(0);
            record.setReport(0);
            model.setData(treeReplyService.saveComment(record));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("发表评论失败");
        }
        return model;
    }

    /**
     * 发布回复
     * @param record 请求体record中应包含 treeholeId-文章id answerId-回复者id content-内容
     * @param request
     * @return
     */
    @PostMapping("/releaseReply")
    public Model releaseReply(@RequestBody TreeReply record, HttpServletRequest request) {
        Model model = new Model();
        try{
            //获取发布者id
            String openid = (String) request.getAttribute("openid");
            Integer replyerId = userService.getUserIdByOpenId(openid);
            record.setReplyerId(replyerId);
            //获取时间
            Date date = new Date();
            record.setTime(date);
            //初始化其他数据
            record.setStatus(1);
            record.setlikes(0);
            record.setReport(0);
            model.setData(treeReplyService.saveReply(record));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("发布回复失败");
        }
        return model;
    }

    /**
     * 对某个评论点赞
     * @param commentId 评论的id
     * @return
     */
    @PostMapping("/giveLike/{id}")
    public Model giveLike(@PathVariable("id") Integer commentId) {
        Model model = new Model();
        try {
            model.setData(treeReplyService.giveLike(commentId));
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
     * @param commentId 被举报的评论id
     * @return
     */
    @PostMapping("/sendReport/{id}")
    public Model sendReport(@PathVariable("id") Integer commentId) {
        Model model = new Model();
        try {
            model.setData(treeReplyService.sendReport(commentId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("点赞失败");
        }
        return model;
    }
}
