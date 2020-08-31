package com.dmz.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Admin {
    @NotNull
    //@Size(min = 6,message = "username length min 6")
    @Size(min = 6,message = "{error.username}")
    String username;
    //@Size(min = 6,max = 10,message = "password length between 6 and 10")
    @Size(min = 6,max = 10,message = "{error.password}")
    String password;
}
