package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.service.UploadFileService;
import com.hust13.wishbottle.utils.UploadFileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * 文件上传服务实现类
 * created by wzy on 2020/2/22
 */
@Service
@Transactional
public class UploadFileServiceImpl implements UploadFileService {

    /**
     * 处理并上传文件
     * @param imagFiles
     * @param voiceFile
     * @return 返回HashMap 包含url信息键值对
     * @throws Exception
     */
    @Override
    public HashMap<String, String> uploadFiles(MultipartFile[] imagFiles, MultipartFile voiceFile) throws Exception {
        HashMap<String, String> filePathMap = new HashMap<>();

        if (imagFiles != null){
            StringBuffer stringBuffer = new StringBuffer();
            //循环遍历上传处理每一个图片文件
            for(MultipartFile imagFile : imagFiles){
                String imagFileName = UploadFileUtil.uploadImagFile(imagFile);
                //多个文件名之间用'+'分隔
                stringBuffer.append(imagFileName + "+");
            }
            //去掉最后一个'-'
            int i = stringBuffer.lastIndexOf("+");
            String imagFilesUrl = stringBuffer.substring(0, i);
            filePathMap.put("imagFilesUrl", imagFilesUrl);
        }

        if(voiceFile != null){
            //上传音频文件
            String voiceFileUrl = UploadFileUtil.uploadVoiceFile(voiceFile);
            filePathMap.put("voiceFileUrl", voiceFileUrl);
        }

        return filePathMap;
    }
}
