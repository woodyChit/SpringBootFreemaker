package com.wd.fileupload;

import com.wd.model.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by woody on 2017/8/6.
 */
@WebServlet("/file/upload")        //also add @ServletComponentScan to springbootapplication.
public class FileuploadServlet extends HttpServlet{

    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init servlet..." );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPost(request, response);      注释掉，不然会有405错误
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        fileUploadService.fileupload(request,response);
        response.setStatus(200);


    }
}
