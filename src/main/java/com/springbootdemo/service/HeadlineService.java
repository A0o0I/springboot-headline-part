package com.springbootdemo.service;

import com.springbootdemo.pojo.Headline;
import com.springbootdemo.pojo.vo.PortalVo;
import com.springbootdemo.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 002
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-09-24 16:37:31
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline,String token);

    Result updateData(Headline headline);
}
