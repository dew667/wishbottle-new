package com.hust13.wishbottle.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * 文件上传服务接口
 * created by wzy on 2020/2/21
 */
public interface UploadFileService {

    HashMap<String, String> uploadFiles(MultipartFile[] imagFiles, MultipartFile voiceFile) throws Exception;
}
