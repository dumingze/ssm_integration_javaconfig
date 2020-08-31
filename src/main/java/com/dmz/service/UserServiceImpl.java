package com.dmz.service;


import com.dmz.bean.User;
import com.dmz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Override
    public User queryUserById(Integer id)
    {

        return userMapper.selectUserById(id);
    }
}
