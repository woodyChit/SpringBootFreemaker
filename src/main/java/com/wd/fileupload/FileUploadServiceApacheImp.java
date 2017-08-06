package com.wd.fileupload;

import com.wd.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by woody on 2017/8/5.
 */
@Service
public class FileUploadServiceApacheImp implements FileUploadService{

    private final static long FILE_MAX_SIZE=512*1024*1024;
    @Override
    public String fileupload(HttpServletRequest request, HttpServletResponse response)   {
        final String ROOTPATH = request.getSession().getServletContext().getRealPath("/");
        final String UPLOAD_FOLDER = ROOTPATH+"uploadfolder";
        File uploadFolder = new File(UPLOAD_FOLDER);
        if(!uploadFolder.exists()) uploadFolder.mkdir();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024);
        factory.setRepository(uploadFolder);
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setSizeMax(FILE_MAX_SIZE);

        ProgressListener listener = new MyFileUploadProgressLisenter(request.getSession());
        fileUpload.setProgressListener(listener);
        List<FileItem> fileList;
        try {
            fileList = fileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
            return "ERROR";
        }
        FileItem fileItem = fileList.get(0);
        String oldFileName = fileItem.getName();
        int indexOf = oldFileName.lastIndexOf(File.separator);
        if(indexOf!=-1){        //IE 和 chrome 的 fileName 不一样，ie 会将路径也传过来， chrome 只会传正确的文件名。
            oldFileName = oldFileName.substring(indexOf+1);
        }
        File newFile = new File(UPLOAD_FOLDER,oldFileName);
        try {
            fileItem.write(newFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
        String token = UUID.randomUUID().toString();
        request.getSession().setAttribute(Constants.SESSION_KEY_FILEUPLOAD, token);
        return token;
    }
}
