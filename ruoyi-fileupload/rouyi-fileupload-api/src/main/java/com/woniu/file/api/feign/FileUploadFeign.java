package com.woniu.file.api.feign;

import com.woniu.file.controller.FileUpliadConreoller;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author ZWL
 * @create 2020/8/17
 * @since 1.0.0
 */
@FeignClient(value = "rouyi-fileupload",fallback = FileUpliadConreoller.class)
public interface FileUploadFeign {

    @RequestMapping("/qiniu/uploadImg")
    public void uploadImg(File file,String key);


}