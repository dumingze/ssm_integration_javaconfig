package com.dmz.controller;


import com.dmz.bean.Admin;
import com.dmz.bean.BaseRespVo;
import com.dmz.bean.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserControllerParamterCheckOut {

    /**
     *
     * @param bindingResult 👉参数校验的结果 → 哪一个参数发生错误了，发生的错误是什么
     */
    @RequestMapping("login")
    public BaseRespVo login(@Valid Admin admin, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            String field = fieldError.getField();
            Object rejectedValue = fieldError.getRejectedValue();
            String defaultMessage = fieldError.getDefaultMessage();
            String message = "参数" + field + "携带了" + rejectedValue + "没有完成校验：" + defaultMessage;
            return BaseRespVo.fail(message);
        }
        return BaseRespVo.ok();
    }
}
