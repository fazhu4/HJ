package com.hj.springai.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @FileName ClockMapper
 * @Description
 * @Author fazhu
 * @date 2024-12-20
 **/
@Mapper
public interface ClockMapper {
    //查最后一次签到
    @Select("select last_day from clockin where id = #{id}")
    String getLastDay(int id);

}
