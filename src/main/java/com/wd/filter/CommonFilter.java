package com.wd.filter;

import com.wd.Constants;
import com.wd.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by wd on 2017/7/13.
 */
@Component
@WebFilter(filterName = "commonFilter",urlPatterns = "/*")
public class CommonFilter extends OncePerRequestFilter{
    @Value("${myhost}")
    private String host;
    @Autowired
    RedisService redisService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletRequest.setAttribute("host","host...");
        redisService.leftPush(Constants.REDIS_KEY_PAGEVIEW,getFullPath(httpServletRequest));
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private String getFullPath (HttpServletRequest httpServletRequest){
        Map<String,String[]> parameterMap = httpServletRequest.getParameterMap();
        StringBuilder sb = new StringBuilder();
        sb.append(httpServletRequest.getServletPath()).append("?");
        for(Map.Entry<String,String[]> entry: parameterMap.entrySet()){
            for(String v:entry.getValue()){
                sb.append(entry.getKey()).append("=").append(v).append("&");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
