package com.hust13.wishbottle.service.impl;

import com.hust13.wishbottle.service.ImagFilesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 图片文件服务实现类
 * created by wzy on 2020/2/22
 */
@Service
@Transactional
public class ImagFilesServiceImpl implements ImagFilesService {

    /**
     * 处理多个图片链接
     * @param imagFiles 图片链接数组
     * @return 拼接后的图片链接
     */
    @Override
    public String makeImagFilesUrl(String[] imagFiles) {
        //若传入的图片链接数组为空 则返回null
        if (imagFiles == null)
            return null;

        StringBuffer stringBuffer = new StringBuffer();
        //循环遍历处理文件链接
        for(String imagFile: imagFiles) {
            //文件链接之间用'+'分隔
            stringBuffer.append(imagFile + "+");
        }

        //去掉最后一个'+'
        int i = stringBuffer.lastIndexOf("+");
        String imagFilesUrl = stringBuffer.substring(0, i);

        return imagFilesUrl;
    }
}
