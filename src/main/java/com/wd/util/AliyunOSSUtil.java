package com.wd.util;

import com.aliyun.oss.OSSClient;

/**
 * Created by woody on 2017/8/6.
 */
public class AliyunOSSUtil {
    private final static String ENDPOINT= "XXXncs.com";
    private final static String ACCESS_KEY_ID = "XXXX
    private final static String ACCESS_KEY_SECRET = "XXXQM7ryb";
    private final static String BUCKET_NAME = "XXXav3c";


    public static OSSClient getInstance(){
        return new OSSClient(ENDPOINT,ACCESS_KEY_ID,ACCESS_KEY_SECRET);
    }

    public static String bucket(){
        return BUCKET_NAME;
    }
}
