package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.Manager;
import com.hust13.wishbottle.mapper.ManagerMapper;
import com.hust13.wishbottle.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * manager业务实现类
 */
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> getAllManager() {
        return managerMapper.getAllManager();
    }

    @Override
    public String deleteById(Integer id) {
        Integer ret = managerMapper.deleteByPrimaryKey(id);
        if(ret > 0)
            return "删除成功";
        else
            throw new RuntimeException("删除失败");
    }
}
