package com.wd.model;

import com.wd.util.JsonUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by woody on 2017/8/6.
 */
public class JsonModel {
    private boolean flag=false;
    private String msg ;
    private Map<String,Object> data = new LinkedHashMap<>();


    public JsonModel put(String key,Object value){
        data.put(key,value);
        return this;
    }
    public JsonModel flag(boolean b){
        flag = b;
        return this;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.convertToJson(this);
    }
}
