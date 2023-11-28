package com.springbootdemo.controller;


import com.springbootdemo.pojo.User;
import com.springbootdemo.service.UserService;
import com.springbootdemo.utils.JwtHelper;
import com.springbootdemo.utils.Result;
import com.springbootdemo.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result=userService.login(user);

        return result;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result=userService.getUserInfo(token);
        return result;

    }

    @PostMapping("checkUserName")
    public Result checkUserName(String username){
        Result result= userService.checkUserName(username);

        return result;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user){

        Result result= userService.regist(user);
        return result;

    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        boolean expiration = jwtHelper.isExpiration(token);

        if(expiration){
            return Result.build(null, ResultCodeEnum.NOTLOGIN);

        }

        return Result.ok(null);

    }


}
