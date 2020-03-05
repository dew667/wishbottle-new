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

    @Override
    public int addManager(Manager manager) {
        managerMapper.insert(manager);
        return 1;
    }

    @Override
    public int judgeManager(int id){
        int type = managerMapper.judgeManager(id);
        if(type==1){
            return 1;
        }
        return 0;
    }

    @Override
    public int managerLogin(String account, String psd) {
        Manager manager = managerMapper.selectByAccount(account);
        if(manager==null){
            return 0;
        }else {
            if(manager.getPsd().equals(psd)){

                return 1;
            }
            return 2;
        }
    }
}
