package com.wd.fileupload;

import com.wd.Constants;
import com.wd.model.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by woody on 2017/8/5.
 */
@Controller
@RequestMapping("/file")
public class FileuploadController {

    @Autowired
    OSSFileUploadService fileUploadService;
    @RequestMapping(value = "/upladPage.html",method = RequestMethod.GET)
    public String uploadpage(){
        return "fileupload";
    }
    @RequestMapping(value = "/ossUpladPage.html",method = RequestMethod.GET)
    public String uploadpageOSS(){
        return "ossfileupload";
    }

    //@RequestMapping(value = "/upload",method = RequestMethod.POST)
    //由于dispatcher经过了multipartResolver，实际上的request中已经没有file信息了。
//    public void fileupload(HttpServletRequest request, HttpServletResponse response){
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        fileUploadService.fileupload(request,response);
//    }

    @PostMapping("/ossupload")
    public void uploadFile(MultipartFile file, HttpServletResponse response){
        fileUploadService.uploadFile(file);
        response.setStatus(200);
    }

    @ResponseBody
    @GetMapping("/progress.json")
    public JsonModel progresss(HttpServletRequest request){
        JsonModel model = new JsonModel();
        Double strProgress = MyFileUploadProgressLisenter.getProgress("no1");
        model.flag(true);
        model.put("progress",strProgress);
        return model;
    }
}
