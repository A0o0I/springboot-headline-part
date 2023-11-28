package com.springbootdemo.service;

import com.springbootdemo.pojo.Type;
import com.springbootdemo.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 002
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-09-24 16:37:31
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
