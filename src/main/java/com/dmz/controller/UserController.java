package com.dmz.controller;


import com.dmz.bean.BaseRespVo;
import com.dmz.bean.User;
import com.dmz.exception.CustomException;
import com.dmz.exception.CustomException2;
import com.dmz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("user/query/{id}")
    public BaseRespVo queryUserById(@PathVariable("id")Integer id){
        User user = userService.queryUserById(id);
        return BaseRespVo.ok(user);
    }




    /**
     * springmvc没有提供一个直接将字符串装换为 日期格式的转换器 → 需要你指定格式
     */
 /*   @RequestMapping("birthday")
    @ResponseBody
    public BaseRespVo birthday(@DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday){
        System.out.println(birthday);
        return BaseRespVo.ok(birthday);
    }*/

    @RequestMapping("birthday")
    @ResponseBody
    public BaseRespVo birthday(Date birthday){
        System.out.println(birthday);
        return BaseRespVo.ok(birthday);
    }

    @RequestMapping("file/upload")
    @ResponseBody
    public BaseRespVo fileUpload(MultipartFile myfile){

        String originalFilename = myfile.getOriginalFilename();
        File file = new File("/Users/dumingze/Desktop/MySpringMVC", originalFilename); //用来接收上传的文件
        try {
            myfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BaseRespVo.ok();
    }


    @RequestMapping("hello")
    public String hello() throws CustomException2 {
        //int i = 1/0;
        if (true){
            throw new CustomException2("异常了", "dmz");//message = 第一个参数 username=第二个参数
        }
        return "/WEB-INF/exception.jsp";
    }
}
