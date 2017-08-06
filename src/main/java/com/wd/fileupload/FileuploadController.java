package com.wd.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by woody on 2017/8/5.
 */
@Controller
@RequestMapping("/file")
public class FileuploadController {

    @RequestMapping(value = "/upladPage.html",method = RequestMethod.GET)
    public String uploadpage(){
        return "fileupload";
    }

    //@RequestMapping(value = "/upload",method = RequestMethod.POST)
    //由于dispatcher经过了multipartResolver，实际上的request中已经没有file信息了。
//    public void fileupload(HttpServletRequest request, HttpServletResponse response){
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        fileUploadService.fileupload(request,response);
//    }
}
