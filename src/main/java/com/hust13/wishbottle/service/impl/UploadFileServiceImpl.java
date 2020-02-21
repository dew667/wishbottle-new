package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.service.UploadFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Override
    public HashMap<String, String> uploadFiles(MultipartFile[] imagFiles, MultipartFile voiceFile) throws Exception {
        return null;
    }
}
