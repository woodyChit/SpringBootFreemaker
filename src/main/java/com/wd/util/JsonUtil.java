package com.wd.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd.entity.User;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by wd on 2017/7/14.
 */
public class JsonUtil {

    public static String convertToJson(Object o){
        ObjectMapper om = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();
        try {
            om.writeValue(stringWriter,o);
            String a = stringWriter.toString();
            if(a!=null){
                return a;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                stringWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

//    public static void main(String[] args) {
//        User u = new User();
//        u.setId(100L);
//        u.setPassword("welfjw");
//        u.setName("chigeo");
//        System.out.println(convertToJson(u));
//        Turple<String,User> t = new Turple<>("usr",u);
//        User a = t.getV();
//        System.out.println(a);
//        System.out.println(u);
//        // ExcetionSystem.out.println(convertToJson(t));
//    }

    public static class Turple<K,V>{
        K k;
        V v;
        public Turple(K k,V v){
            this.k = k ;
            this.v = v ;
        }

        public K getK() {
            return k;
        }

        public V getV() {
            return v;
        }
    }
}
