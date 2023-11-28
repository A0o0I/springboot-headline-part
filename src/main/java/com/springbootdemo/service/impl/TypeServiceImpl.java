package com.springbootdemo.service.impl;

import com.springbootdemo.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootdemo.pojo.Type;
import com.springbootdemo.service.TypeService;
import com.springbootdemo.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 002
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2023-09-24 16:37:31
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{


    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }
}




