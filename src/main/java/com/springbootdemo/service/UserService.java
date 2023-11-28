package com.springbootdemo.service;

import com.springbootdemo.pojo.User;
import com.springbootdemo.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 002
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-09-24 16:37:31
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);
}
