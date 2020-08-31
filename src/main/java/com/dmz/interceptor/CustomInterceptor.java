package com.dmz.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Springmvc运行时候，在handler方法执行前后，会执行的方法
@Component
public class CustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

     /*   HandlerMethod handlerMethod =   (HandlerMethod) handler;
        System.out.println(handlerMethod.toString());

        System.out.println(modelAndView==null);*/
        // modelAndView.setViewName("/WEB-INF/jsp/post.jsp");
        //如果响应的是json数据，也可以做后处理
        //response.getWriter().append("11111111111111111111"); 老师讲错了，这样写不行
     /*   String arr="111111";
        byte[] bytes = arr.getBytes();

        System.out.println(bytes);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.cla();
        outputStream.write(bytes,0,4);
        System.out.println("postHandle");*/

        System.out.println("正在执行");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
