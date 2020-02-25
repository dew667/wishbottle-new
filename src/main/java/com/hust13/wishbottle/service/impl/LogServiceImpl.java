package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.Log;
import com.hust13.wishbottle.mapper.LogMapper;
import com.hust13.wishbottle.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 日记service实现类
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    /**
     * 写日记
     * @param log
     * @return
     */
    @Override
    public Log writeLog(Log log) {
        logMapper.insertSelective(log);
        return log;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public String deleteLog(Integer id){
        Integer ret = logMapper.deleteByPrimaryKey(id);
        if(ret > 0)
            return "删除成功";
        else
            throw new RuntimeException("删除失败 不存在该项日记");
    }

    /**
     * 获取所有日记
     * @param writerId
     * @return
     */
    @Override
    public List<Log> getMyLogs(int writerId) {
        List<Log> logs = logMapper.getMyLogs(writerId);
        return logs;
    }

    /**
     * 读取指定日记
     * @param id
     * @return
     */
    @Override
    public Log getOneLog(Integer id) {
        Log log = logMapper.selectByPrimaryKey(id);
        return log;
    }

    /**
     * 修改
     * @param log
     * @return
     */
    @Override
    public String updateLog(Log log) {
        Integer ret = logMapper.updateByPrimaryKeySelective(log);
        if(ret > 0)
            return "修改成功";
        else
            throw new RuntimeException("修改数据库时出错");
    }

    /**
     * 根据关键词keywords和本人id搜索
     * @param map
     * @return
     */
    @Override
    public List<Log> searchInLog(Map<String, Object> map) {
        List<Log> logs = logMapper.searchInLog(map);
        return logs;
    }
}
