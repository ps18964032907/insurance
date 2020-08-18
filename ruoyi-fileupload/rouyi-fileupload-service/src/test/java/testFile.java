import com.woniu.file.util.QiniuCloudUtil;
import org.junit.jupiter.api.Test;

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
public class testFile {

    @Test
    public void fileupload() throws IOException {
        File file = new File("E:\\Photos\\file1.jpg");
        String upload = QiniuCloudUtil.upload(file,"aaaa");
        System.out.println(upload);

    }

}