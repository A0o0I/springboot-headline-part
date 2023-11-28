package com.springbootdemo.inteceptors;

import com.springbootdemo.utils.JwtHelper;
import com.springbootdemo.utils.Result;
import com.springbootdemo.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginProtectedInteceptor implements HandlerInterceptor {


    @Autowired
    private JwtHelper jwtHelper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");

        boolean expiration = jwtHelper.isExpiration(token);

        if(!expiration){
            return true;
        }

        Result result=Result.build(null, ResultCodeEnum.NOTLOGIN);

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(result);

        response.getWriter().print(json);

        return false;
    }
}
