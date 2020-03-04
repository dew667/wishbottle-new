package com.hust13.wishbottle.controller.weixin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust13.wishbottle.entity.Log;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.LogService;
import com.hust13.wishbottle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日记控制器
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    /**
     * 写日志
     * @param log 请求体中应该包含 title content weather emotion
     * @return
     */
    @PostMapping("/write")
    public Model writeLog(@RequestBody Log log, HttpServletRequest request){
        Model model = new Model();
        try {
            //查询本人id
            String openid = (String) request.getAttribute("openid");
            Integer userId = userService.getUserIdByOpenId(openid);
            //id封装进log实体
            log.setWriterId(userId);
            //封装时间
            log.setTime(new Date());
            //设置状态为发布
            log.setStatus(1);
            model.setData(logService.writeLog(log));
        }catch (Exception e){
            model.setCode(1);
            model.setMsg("填写失败");
        }
        return model;
    }

    /**
     * 删除日志
     * @param id 指定要删除的日记id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Model deleteLog(@PathVariable("id") Integer id){
        Model model = new Model();
        try {
            model.setData(logService.deleteLog(id));
        }catch (Exception e){
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("删除失败");
        }
        return model;
    }

    /**
     * 分页查看自己的日志列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getAll/{pageNum}/{pageSize}")
    public Model getMyLog(HttpServletRequest request, @PathVariable("pageNum") Integer pageNum,
                          @PathVariable("pageSize") Integer pageSize){
        Model model = new Model();
        try {
            //获取本人即作者id
            String openid = (String) request.getAttribute("openid");
            Integer writerId = userService.getUserIdByOpenId(openid);
            //按时间升序排列
            String sort = "time desc";
            PageHelper.startPage(pageNum,pageSize,sort);
            //通过作者id获取所有文章
            PageInfo pageInfo=new PageInfo(logService.getMyLogs(writerId));
            model.setData(pageInfo);
        }catch (Exception e){
            model.setCode(1);
            model.setMsg("查看失败");
        }
        return model;
    }

    /**
     * 读取指定日记
     * @param id 日记id
     * @return
     */
    @GetMapping("/readLog/{id}")
    public Model readOneLog(@PathVariable("id") Integer id) {
        Model model = new Model();
        try {
            model.setData(logService.getOneLog(id));
        }catch (Exception e){
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("读取失败");
        }
        return model;
    }

    /**
     * 修改日记
     * @param log 请求体传入参数包括 id-必须 content title time status emotion weather
     * @return
     */
    @PutMapping("/update")
    public Model updateLog(@RequestBody Log log) {
        Model model = new Model();
        try {
            model.setData(logService.updateLog(log));
        }catch (Exception e){
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("修改失败");
        }
        return model;
    }

    /**
     * 后期可以考虑使用Elasticsearch
     * 根据关键词搜索本人日记 返回分页结果
     * @param keywords 关键词
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @GetMapping("/search/{pageNum}/{pageSize}")
    public Model searchKeywords(@RequestParam("keywords") String keywords,
                                @PathVariable("pageNum") Integer pageNum,
                                @PathVariable("pageSize") Integer pageSize,
                                HttpServletRequest request) {
        Model model = new Model();
        try {
            //获取本人即作者id
            String openid = (String) request.getAttribute("openid");
            Integer writerId = userService.getUserIdByOpenId(openid);
            //按时间升序排列
            String sort = "time asc";
            PageHelper.startPage(pageNum,pageSize,sort);
            //封装数据
            Map<String, Object> map = new HashMap<>();
            map.put("writerId", writerId);
            map.put("keywords", keywords);
            //通过作者id获取所有文章
            PageInfo pageInfo=new PageInfo(logService.searchInLog(map));
            model.setData(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            model.setCode(1);
            model.setMsg("搜索失败");
        }
        return model;
    }
}
