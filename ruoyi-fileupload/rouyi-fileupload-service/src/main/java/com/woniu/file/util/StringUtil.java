package com.woniu.file.util;

import cn.hutool.core.date.DateUtil;

import java.util.UUID;

/**
 * 功能描述:<br>
 * 〈为图片生成唯一的ID〉
 *
 * @author ZWL
 * @create 2020/8/17
 * @since 1.0.0
 */
public class StringUtil {

    public static String getRanddomImgName(String fileName){

        int index = fileName.lastIndexOf(".");

        if ((fileName == null || fileName.isEmpty()) || index == -1){
            throw new IllegalArgumentException();
        }

        // 获取文件后缀
        String suffix = fileName.substring(index);
        // 生成UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 生成上传至云服务器的路径
        String path = "code/duck/" + DateUtil.today() + "-" + uuid + suffix;
        return path;

    }

}