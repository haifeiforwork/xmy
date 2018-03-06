package com.zfj.xmy.pc.web.filter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CacheFilter implements ApplicationContextAware, Filter {
    private static final Logger log = LoggerFactory
            .getLogger(CacheFilter.class);

   
    private static ApplicationContext ctx;

    @Override
    public void init(FilterConfig config) throws ServletException {
        
      
        ServletContext servletContext = config.getServletContext();  
        ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
       
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
       
        System.out.println(req.getRequestURI());
        // 如果不是访问主页，放行
        if (false == req.getRequestURI().equals("/xmy-pc-web/")) {
            filterChain.doFilter(servletRequest, resp);
            return;
        }

        // 访问的是主页
        // 从缓存中得到主页html
        String html = getHtmlFromCache();
        if (null == html) {
            // 缓存中没有
            log.info("缓存不存在，生成缓存");
            ResponseWrapper wrapper = new ResponseWrapper(resp);
           
            filterChain.doFilter(servletRequest, wrapper);

           
            // 放入缓存
            html = wrapper.getResult();
            putIntoCache(html);

        }

        // 返回响应
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().print(html);
    }

    @Override
    public void destroy() {

    }


    private String getHtmlFromCache() {
        StringRedisTemplate redis = (StringRedisTemplate) ctx
                .getBean("redisTemplate");
        return redis.opsForValue().get("home");
    }

    private void putIntoCache(String html) {
        StringRedisTemplate redis = (StringRedisTemplate) ctx
                .getBean("redisTemplate");
        redis.opsForValue().set("home", html, TimeUnit.MINUTES.toSeconds(10)); // 10分钟
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        // TODO Auto-generated method stub
        
    }

}
