package com.springbootdemo.mapper;

import com.springbootdemo.pojo.Headline;
import com.springbootdemo.pojo.vo.PortalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 002
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-09-24 16:37:31
* @Entity com.springbootdemo.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {


    IPage<Map> selectMyPage(IPage page,@Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(Integer hid);
}




