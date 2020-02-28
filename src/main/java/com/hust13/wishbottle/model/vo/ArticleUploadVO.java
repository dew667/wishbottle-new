package com.hust13.wishbottle.model.vo;

/**
 * 树洞文章上传发布封装类
 * created by wzy on 2020/2/21
 */
public class ArticleUploadVO {

    //标题
    private String title;

    //内容
    private String content;

    //图片文件名数组 可能有多个图片
    String[] imagFiles;

    //音频文件名
    String voiceFile;

    public String[] getImagFiles() {
        return imagFiles;
    }

    public void setImagFiles(String[] imagFiles) {
        this.imagFiles = imagFiles;
    }

    public String getVoiceFile() {
        return voiceFile;
    }

    public void setVoiceFile(String voiceFile) {
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
