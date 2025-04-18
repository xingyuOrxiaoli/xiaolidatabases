package com.xiaoli.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @program: my-blog
 * @classname: UploadFileUtils
 * @description: 上传文件工具类
 **/
public class UploadFileUtils {

    /**
     * @Description: 获取图片后缀
     * @Param: [file]
     */
    public static String getSuffixName(MultipartFile file){
        String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName)){
            throw new RuntimeException("获取图片后缀失败");
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
    
    /**
     * @Description: 生成文件名称通用方法
     * @Param: [suffixName] 图片后缀
     */
    public static String getNewFileName(String suffixName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        int random = new Random().nextInt(100);
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(random).append(suffixName);
        return tempName.toString();
    }
}
