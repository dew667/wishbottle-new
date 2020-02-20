package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.TreeLog;
import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.entity.Treehole;

import java.util.List;

public interface TreeholeService {

    //获取所有私有树洞信息
    public List<Treehole> getTreehole();

    //获取所有公开树洞信息
    public List<TreeLog> getTreeLog();

    //查看自己私有树洞
    public List<Treehole> getMyTreehole(int userId);

    //查看自己公开树洞
    public List<TreeLog> getMyTreeLog(int userId);

    //写私有树洞信息
    public int writeTreehole(Treehole treehole);

    //写公开树洞信息
    public int writeTreeLog(TreeLog treeLog);

    //写树洞回复
    public int writeTreeReply(TreeReply treeReply);




}
