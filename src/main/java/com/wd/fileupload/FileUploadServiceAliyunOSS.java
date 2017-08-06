package com.wd.fileupload;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.wd.util.AliyunOSSUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by woody on 2017/8/6.
 */
@Service
public class FileUploadServiceAliyunOSS implements OSSFileUploadService{

    @Override
    public String uploadFile(MultipartFile file) {

        OSSClient ossClient = AliyunOSSUtil.getInstance();
        String oFileName = file.getOriginalFilename();
        try {
            ossClient.putObject(AliyunOSSUtil.bucket(),oFileName,file.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "OK";

    }
}
