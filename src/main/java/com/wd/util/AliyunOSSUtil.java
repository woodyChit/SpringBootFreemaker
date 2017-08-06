package com.wd.util;

import com.aliyun.oss.OSSClient;

/**
 * Created by woody on 2017/8/6.
 */
public class AliyunOSSUtil {
    private final static String ENDPOINT= "oss-cn-beijing.aliyuncs.com";
    private final static String ACCESS_KEY_ID = "LTAIvL3yiEW9YKve";
    private final static String ACCESS_KEY_SECRET = "A7z0KyO5vJG44JKulzgQM7ryb";
    private final static String BUCKET_NAME = "wdbucket199102Nav3c";


    public static OSSClient getInstance(){
        return new OSSClient(ENDPOINT,ACCESS_KEY_ID,ACCESS_KEY_SECRET);
    }

    public static String bucket(){
        return BUCKET_NAME;
    }
}
