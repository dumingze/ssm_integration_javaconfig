package com.dmz.exception;


import com.dmz.bean.BaseRespVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
//@ResponseBody//写在类上，意味着当前类的全部handler方法返回的都是json

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({CustomException.class})
    public String customException(CustomException e){
        String username = e.getUsername();
        String message = e.getMessage();
        return "/WEB-INF/custom.jsp";//restful,不会显示的
    }

    @ExceptionHandler({CustomException2.class})
    //@ResponseBody
    public BaseRespVo customException2(CustomException2 e){

        return BaseRespVo.fail("songgeniupile");
    }
}
