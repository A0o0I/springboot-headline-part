package com.springbootdemo.service.impl;

import com.springbootdemo.pojo.vo.PortalVo;
import com.springbootdemo.utils.JwtHelper;
import com.springbootdemo.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootdemo.pojo.Headline;
import com.springbootdemo.service.HeadlineService;
import com.springbootdemo.mapper.HeadlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 002
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2023-09-24 16:37:31
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    JwtHelper jwtHelper=new JwtHelper();

    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {


        IPage<Map> page=new Page<>(portalVo.getPageNum(), portalVo.getPageSize());


        headlineMapper.selectMyPage(page,portalVo);

        List<Map> records=page.getRecords();
        Map data=new HashMap<>();
        data.put("pageData",records);
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPage",page.getPages());
        data.put("totalSize",page.getTotal());

        Map pageInfo=new HashMap<>();
        pageInfo.put("pageInfo",data);

        return Result.ok(pageInfo);


    }

    @Override
    public Result showHeadlineDetail(Integer hid) {

        Map headLineDetail=headlineMapper.selectDetailMap(hid);

        Map headlineMap= new HashMap<>();
        headlineMap.put("headline",headLineDetail);

        Headline headline=new Headline();
        headline.setHid((Integer) headLineDetail.get("hid"));

        headline.setVersion((Integer) headLineDetail.get("version"));

        headline.setPageViews((Integer) headLineDetail.get("pageViews")+1);

        headlineMapper.updateById(headline);

        return Result.ok(headlineMap);


    }

    @Override
    public Result publish(Headline headline,String token) {

        int userId = jwtHelper.getUserId(token).intValue();

        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        headlineMapper.insert(headline);

        return Result.ok(null);


    }

    @Override
    public Result updateData(Headline headline) {

        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

        headline.setVersion(version);

        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);

        return Result.ok(null);


    }
}




