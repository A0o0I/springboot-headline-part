package com.springbootdemo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.springbootdemo.utils.JwtHelper;
import com.springbootdemo.utils.MD5Util;
import com.springbootdemo.utils.Result;
import com.springbootdemo.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootdemo.pojo.User;
import com.springbootdemo.service.UserService;
import com.springbootdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 002
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-09-24 16:37:31
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    UserMapper userMapper;


    @Autowired
    private JwtHelper jwtHelper;


    @Override
    public Result login(User user) {

        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());

        User loginUser = userMapper.selectOne(lambdaQueryWrapper);

        if(loginUser==null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }


        if(!StringUtils.isEmpty(user.getUserPwd())
                && MD5Util.encrypt(user.getUserPwd())
                        .equals(loginUser.getUserPwd())){

            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map data=new HashMap<>();
            data.put("token",token);

            return Result.ok(data);
        }


        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {

        boolean expiration = jwtHelper.isExpiration(token);

        if(expiration){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

        int userId = jwtHelper.getUserId(token).intValue();

        User user = userMapper.selectById(userId);
        user.setUserPwd("");

        Map data=new HashMap<>();
        data.put("loginUser",user);

        return Result.ok(data);


    }

    @Override
    public Result checkUserName(String username) {

        LambdaQueryWrapper<User> queryWrapper
                =new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);


        Long count = userMapper.selectCount(queryWrapper);

        if(count==0){
            return Result.ok(null);

        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    @Override
    public Result regist(User user) {

        LambdaQueryWrapper<User> queryWrapper
                =new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());


        Long count = userMapper.selectCount(queryWrapper);

        if(count>0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        userMapper.insert(user);

        return Result.ok(null);

    }
}




