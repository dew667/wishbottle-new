package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.Manager;
import com.hust13.wishbottle.mapper.ManagerMapper;
import com.hust13.wishbottle.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    @Override
    public int judgeManager(int managerId){
        return 0;
    }

    @Override
    public int addManager(Manager manager) {
        managerMapper.insert(manager);
        return 1;
    }

    @Override
    public int deleteManager(int managerId) {
        return 0;
    }

    @Override
    public List<Manager> getManagers() {
        return null;
    }
}
