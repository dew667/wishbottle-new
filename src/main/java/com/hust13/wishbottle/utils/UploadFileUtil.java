package com.hust13.wishbottle.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 文件上传util
 * created by wzy on 2020/2/22
 */
public class UploadFileUtil {

    //文件保存目录 物理路径
    private static final String rootPath = "D:\\软件工程\\心愿瓶\\resource";

    //允许的图片格式
    private static final String allowImagSuffix = ".bmp.jpg.jpeg.png.gif";

    //允许的音频格式
    private static final String allowVoiceSuffix = ".mp3.aac.wav.PCM";

    //上传图片文件
    public static String uploadImagFile(MultipartFile imagFile) throws Exception {
        String suffix = getSuffix(imagFile);
        if(allowImagSuffix.indexOf(suffix) == -1){
            throw new RuntimeException("不允许的图片格式");
        }
        File descFile = createDescFile(suffix);
        //将文件写入磁盘
        imagFile.transferTo(descFile);
        return descFile.getName();
    }

    //上传音频文件
    public static String uploadVoiceFile(MultipartFile voiceFile) throws Exception {
        String suffix = getSuffix(voiceFile);
        if(allowVoiceSuffix.indexOf(suffix) == -1){
            throw new RuntimeException("不允许的音频格式");
        }
        File descFile = createDescFile(suffix);
        //将文件写入磁盘
        voiceFile.transferTo(descFile);
        return descFile.getName();
    }

    //获取文件名后缀
    private static String getSuffix(MultipartFile file){
        //文件的完整名称
        String originalFilename = file.getOriginalFilename();
        //文件后缀如.png
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        return suffix;
    }

    //创建目标文件
    private static File createDescFile(String suffix) throws RuntimeException{
        //生成文件名
        String fileName = UUID.randomUUID().toString();
        File descFile = null;
        //如果是图片类型
        if(allowImagSuffix.indexOf(suffix) != -1){
            descFile = new File(rootPath + File.separator + "imag" + File.separator
            + fileName + suffix);
        } else if(allowVoiceSuffix.indexOf(suffix) != -1){ //音频类型
            descFile = new File(rootPath + File.separator + "voice" + File.separator
            + fileName + suffix);
        } else{
            throw new RuntimeException("错误的文件类型");
        }
        //判断目标文件所在的目录是否存在
        if (!descFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            descFile.getParentFile().mkdirs();
        }
        return descFile;
    }
}
