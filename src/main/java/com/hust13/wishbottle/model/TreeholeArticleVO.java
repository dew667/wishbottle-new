package com.hust13.wishbottle.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * 树洞文章上传发布封装类
 * created by wzy on 2020/2/21
 */
public class TreeholeArticleVO {

    //标题
    private String title;

    //内容
    private String content;

    //图片 可能有多张
    MultipartFile [] imagFiles;

    //音频
    MultipartFile voiceFile;

    public MultipartFile[] getImagFiles() {
        return imagFiles;
    }

    public void setImagFiles(MultipartFile[] imagFiles) {
        this.imagFiles = imagFiles;
    }

    public MultipartFile getVoiceFile() {
        return voiceFile;
    }

    public void setVoiceFile(MultipartFile voiceFile) {
        this.voiceFile = voiceFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
