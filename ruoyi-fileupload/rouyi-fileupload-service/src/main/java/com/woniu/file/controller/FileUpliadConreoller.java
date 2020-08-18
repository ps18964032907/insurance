package com.woniu.file.controller;

import com.woniu.file.util.QiniuCloudUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author ZWL
 * @create 2020/8/17
 * @since 1.0.0
 */
@RestController
@RequestMapping("/qiniu")
public class FileUpliadConreoller {


    @RequestMapping("/uploadImg")
    public String uploadImg(File file, String key) throws IOException {

        if (QiniuCloudUtil.upload(file, key) == null){
            return "上传失败!";
        }
        return "上传成功";

    }


}