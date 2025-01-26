package com.hj.springai.mapper;

import com.hj.springai.entity.Active;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ActiveMapper {
    //获取未开始的活动
    @Select("select `img`,`name` from `active` where `status` = 1")
    List<Active> getActiveNoStart();
    //获取已开始的活动
    @Select("select `img`,`name` from `active` where `status` = 2")
    List<Active> getActiveStart();
    //
}
