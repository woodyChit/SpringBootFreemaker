package com.wd.filter;

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

/**
 * Created by wd on 2017/7/13.
 */
@Component
@WebFilter(filterName = "commonFilter",urlPatterns = "/*")
public class CommonFilter extends OncePerRequestFilter{
    @Value("${myhost}")
    private String host;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletRequest.setAttribute("host","host...");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
