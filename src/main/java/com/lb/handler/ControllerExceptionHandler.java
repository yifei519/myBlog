package com.lb.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//用于拦截所有的异常进行统一处理
@ControllerAdvice//用于拦截所有标注含有Controller注解的控制器
public class ControllerExceptionHandler {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)//用于标识该方法是做异常处理的，加上该注解才有效
    public ModelAndView exceptionHander(HttpServletRequest request,Exception e ) throws Exception {
    logger.error("Request URL : {},Exception : {}",request.getRequestURI(),e);
    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
    throw e;
    }
    ModelAndView mv=new ModelAndView();
    mv.addObject("url",request.getRequestURI());
    mv.addObject("exception",e);
    mv.setViewName("error/error");
        return mv;
    }

}
