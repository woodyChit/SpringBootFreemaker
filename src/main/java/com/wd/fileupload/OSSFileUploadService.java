package com.wd.fileupload;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by woody on 2017/8/6.
 */
public interface OSSFileUploadService {
    String uploadFile(MultipartFile file);
}
