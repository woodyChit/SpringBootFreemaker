package com.wd.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;


/**
 * Created by wd on 2017/7/14.
 */
public class JsonUtil {

    public static String convertToJson(Object o){
        ObjectMapper om = new ObjectMapper();
        String re;
        try {
            //om.configure(SerializationFeature.INDENT_OUTPUT, true);     // 为了使JSON视觉上的可读性，在生产中不需如此，会增大Json的内容
            om.setSerializationInclusion( JsonInclude.Include.NON_EMPTY);
            re=om.writeValueAsString(o);
            return re;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T convertToObject(String json ,Class<T> clazz){
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readValue(json,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
