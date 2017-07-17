package com.wd.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wd on 2017/7/17.
 */
public class CookieUtil {

    /**
     * 取得cookie
     * @param cookieName
     * @return
     */
    public static Cookie get(String cookieName, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(StringUtils.isEmpty(cookieName)||cookies==null){
            return null;
        }
        for(Cookie cookie :cookies){
            if(cookie.getName().equals(cookieName)){
                return cookie;
            }
        }
        return null;
    }

    /**
     * 取得cookieValue
     * @param cookieName
     * @return
     */
    public static String get(String cookieName, String cookies){
        if(StringUtils.isEmpty(cookieName)||cookies==null||cookies.length()==0||!cookieName.contains(cookieName)){
            return null;
        }
        String[] pairs = cookies.split(";");
        for(String pair : pairs){
            if(pair.contains(cookieName)){
                String[] kv = pair.split("=");
                if(kv[1]!=null && kv[1].length()!=0){
                    return kv[1].trim();
                }
            }
        }
        return null;
    }
}
