package com.wd.fileupload;


import com.wd.Constants;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by woody on 2017/8/5.
 */
public class MyFileUploadProgressLisenter implements ProgressListener {

    private static Map<String,MyFileUploadProgressLisenter> holder = new HashMap<>();
    private String name;
    private Double progress;
    public MyFileUploadProgressLisenter(String name){
        this.name=name;
        holder.put(name,this);
    }

    @Override
    public void update(long bytesRead, long contentLength, int items) {
        progress =( (100.0)*bytesRead )/ contentLength;
        progress = Math.round(progress*100)/100.0;
        if(progress>99.99){
            holder.remove(this.name);
        }
    }

    public static Double getProgress(String name){
        MyFileUploadProgressLisenter lisenter = holder.get(name);
        if(lisenter!=null){
            return lisenter.progress;
        }else{
            return null;
        }
    }
}
