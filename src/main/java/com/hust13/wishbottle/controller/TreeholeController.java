package com.hust13.wishbottle.controller;

import com.hust13.wishbottle.entity.TreeLog;
import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.model.Model;
import com.hust13.wishbottle.service.TreeholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treehole")
public class TreeholeController {

    @Autowired
    TreeholeService treeholeService;

    /**
     * 获取所有私有树洞
     * @return
     */
    @RequestMapping("/gettreehole")
    public Model getTreehole(){
        Model model = new Model();
        try {
            model.setData(treeholeService.getTreehole());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }

    /**
     * 获取所有公开树洞
     * @return
     */
    @RequestMapping("/gettreelog")
    public Model getTreeLog(){
        Model model = new Model();
        try {
            model.setData(treeholeService.getTreeLog());
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("获取失败");
        }
        return model;
    }


    /**
     * 查看自己私有树洞
     * @param userId
     * @return
     */
    @RequestMapping("/getmytreehole")
    public Model getMyTreehole(int userId){
        Model model = new Model();
        try {
            model.setData(treeholeService.getMyTreehole(userId));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("查看失败");
        }
        return model;
    }

    /**
     * 查看自己公开树洞
     * @param userId
     * @return
     */
    @RequestMapping("/getmytreelog")
    public Model getMyTreeLog(int userId){
        Model model = new Model();
        try {
            model.setData(treeholeService.getMyTreeLog(userId));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("查看失败");
        }
        return model;
    }

    /**
     * 发布私有树洞
     * @param treehole
     * @return
     */
    @RequestMapping("/writetreehole")
    public Model writeTreehole(Treehole treehole){
        Model model = new Model();
        try {
            model.setData(treeholeService.writeTreehole(treehole));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("发布失败");
        }
        return model;
    }

    /**
     * 发布公开树洞
     * @param treeLog
     * @return
     */
    @RequestMapping("/writetreelog")
    public Model writeTreeLog(TreeLog treeLog){
        Model model = new Model();
        try {
            model.setData(treeholeService.writeTreeLog(treeLog));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("发布失败");
        }
        return model;
    }

    /**
     * 填写树洞回复
     * @param treeReply
     * @return
     */
    @RequestMapping("/writetreereply")
    public Model writeTreeReply(TreeReply treeReply){
        Model model = new Model();
        try {
            model.setData(treeholeService.writeTreeReply(treeReply));
        }
        catch (Exception e)
        {
            model.setCode(1);
            model.setMsg("填写失败");
        }
        return model;
    }

}
