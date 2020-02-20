package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.TreeLog;
import com.hust13.wishbottle.entity.TreeReply;
import com.hust13.wishbottle.entity.Treehole;
import com.hust13.wishbottle.mapper.LogMapper;
import com.hust13.wishbottle.mapper.TreeReplyMapper;
import com.hust13.wishbottle.mapper.TreeholeMapper;
import com.hust13.wishbottle.service.TreeholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeholeServiceImpl implements TreeholeService {

    @Autowired
    TreeReplyMapper treeReplyMapper;

    @Autowired
    LogMapper treeLogMapper;

    @Autowired
    TreeholeMapper treeholeMapper;

    @Override
    public List<Treehole> getTreehole() {
        return null;
    }

    @Override
    public List<TreeLog> getTreeLog() {
        return null;
    }

    @Override
    public List<Treehole> getMyTreehole(int userId) {
        return null;
    }

    @Override
    public List<TreeLog> getMyTreeLog(int userId) {
        return null;
    }

    @Override
    public int writeTreehole(Treehole treehole) {
        return 0;
    }

    @Override
    public int writeTreeLog(TreeLog treeLog) {
        return 0;
    }

    @Override
    public int writeTreeReply(TreeReply treeReply) {
        return 0;
    }
}
