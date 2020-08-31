package com.dmz.mapper;


import com.dmz.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectUserById(@Param("id") Integer id);
}
