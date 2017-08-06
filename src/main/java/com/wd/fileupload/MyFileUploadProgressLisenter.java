package com.wd.fileupload;


import com.wd.Constants;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;

/**
 * Created by woody on 2017/8/5.
 */
public class MyFileUploadProgressLisenter implements ProgressListener {
    private HttpSession session;
    public MyFileUploadProgressLisenter(HttpSession session){
        this.session = session;
    }

    @Override
    public void update(long bytesRead, long contentLength, int items) {
        double progress = bytesRead / contentLength * 1.0*100;
        progress = Math.round(progress*100)/100.0;
        session.setAttribute(Constants.SESSION_KEY_FILEUPLOAD,progress);
    }
}
