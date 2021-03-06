package com.woniu.file.util;

import java.io.File;
import java.io.IOException;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import org.springframework.util.StringUtils;

/**
 * @author yintianhao
 * @createTime 07 21:07
 * @description 七牛云工具
 */
public class QiniuCloudUtil {

    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "fciXxzQMuRwZHJ1cfYuLjNKaC2xtf2S3rOuAxBNO";
    private static final String SECRET_KEY = "6r4UBvJpfY2LbKl-bth2FidABjj9x7B9zY3jSWfG";

    // 要上传的空间名
    private static final String bucketname = "pansheng001";

    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    //上传
    public static String upload(File file, String key) throws IOException {
        // 创建上传对象，Zone*代表地区
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        try {
            // 调用put方法上传
            String token = auth.uploadToken(bucketname);
            if(StringUtils.isEmpty(token)) {
                System.out.println("未获取到token，请重试！");
                return null;
            }
            System.out.print("File name = "+file.getName());
            Response res = uploadManager.put(file,key,token);
            // 打印返回的信息
            if (res.isOK()){
                return key;
            }
        }catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            e.printStackTrace();
            System.out.println("error "+r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                System.out.println("error "+e1.error());
                return e1.error();

            }
            return e.error();
        }
        return null;
    }
}
