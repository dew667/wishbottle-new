package com.hust13.wishbottle.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * 图片文件服务接口
 * created by wzy on 2020/2/21
 */
public interface ImagFilesService {

    String makeImagFilesUrl(String[] imagFiles);
}
