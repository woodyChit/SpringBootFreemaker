package com.wd.fileupload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by woody on 2017/8/5.
 */
public interface FileUploadService {
    String fileupload(HttpServletRequest request, HttpServletResponse response);
}
