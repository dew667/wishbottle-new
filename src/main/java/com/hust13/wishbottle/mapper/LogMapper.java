package com.hust13.wishbottle.mapper;

import com.hust13.wishbottle.entity.Log;

import java.util.List;
import java.util.Map;

public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    List<Log> getMyLogs(int writerId);

    List<Log> searchInLog(Map<String, Object> map);
}