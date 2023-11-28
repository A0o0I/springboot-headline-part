package com.springbootdemo.controller;

import com.springbootdemo.pojo.vo.PortalVo;
import com.springbootdemo.service.HeadlineService;
import com.springbootdemo.service.TypeService;
import com.springbootdemo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;


    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result=typeService.findAllTypes();
        return result;

    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo ){
        Result result=headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result=headlineService.showHeadlineDetail(hid);
        return result;
    }




}