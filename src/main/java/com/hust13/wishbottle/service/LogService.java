package com.hust13.wishbottle.service;

import com.hust13.wishbottle.entity.Log;

import java.util.List;
import java.util.Map;

/**
 * 日记service接口
 */
public interface LogService {

    //写日志
    Log writeLog(Log log);

    //删除日志
    String deleteLog(Integer id);

    //查看自己的日志
    List<Log> getMyLogs(int writerId);

    //读取指定文章
    Log getOneLog(Integer id);

    //修改日记
    String updateLog(Log log);

    //搜索内容 返回日记列表
    List<Log> searchInLog(Map<String, Object> map);

}
