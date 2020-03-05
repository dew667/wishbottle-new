package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.entity.IndexPic;
import com.hust13.wishbottle.mapper.IndexPicMapper;
import com.hust13.wishbottle.service.IndexPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 轮播图业务实现类
 */
@Service
@Transactional
public class IndexPicServiceImpl implements IndexPicService {

    @Autowired
    private IndexPicMapper indexPicMapper;

    /**
     * 获取所有轮播图
     * @return
     */
    @Override
    public List<IndexPic> getAll() {
        return indexPicMapper.getAll();
    }

    @Override
    public int insert(MultipartFile file) throws IOException {
        if(null!=file){
            String fileOrigName=file.getOriginalFilename();// 文件原名称
            if (!fileOrigName.contains(".")) {
                throw new IllegalArgumentException("缺少后缀名");
            }
            //获取后缀名
            fileOrigName = fileOrigName.substring(fileOrigName.lastIndexOf("."));
            String newdestPath="D:\\"+ UUID.randomUUID().toString().replace("-","-") +fileOrigName;
            File newfile=new File(newdestPath);
            file.transferTo(newfile);

            IndexPic indexPic=new IndexPic();
            indexPic.setPic(newdestPath);
            indexPic.setTime(new Date());
            return indexPicMapper.insert(indexPic);
        }else {
            return 0;
        }
    }
}
