package com.wd.fileupload;

import com.wd.Constants;
import com.wd.model.JsonModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;


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
