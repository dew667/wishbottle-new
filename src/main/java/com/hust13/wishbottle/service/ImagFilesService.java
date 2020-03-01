package com.hust13.wishbottle.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

/**
 * 图片文件服务接口
 * created by wzy on 2020/2/21
 */
public interface ImagFilesService {

    //生成拼接的图片链接
    String makeImagFilesUrl(String[] imagFiles);

    //逆向操作
    String[] spiltImagFilesUrl(String imagUrl);
}
