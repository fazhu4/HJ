package com.hj.springai.mapper;

import com.hj.springai.entity.Active;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Time;
import java.util.List;

@Mapper
public interface ActiveMapper {
    //获取未开始的活动
    @Select("select `img`,`name` from `active` where `status` = 1")
    List<Active> getActiveNoStart();
    //获取已开始的活动
    @Select("select `img`,`name` from `active` where `status` = 2")
    List<Active> getActiveStart();
    //创建活动
    @Insert("insert into `active`(`name`,`content`,`img`,`places`,`remainder_places`,`sign_start_time`,`sign_end_time`,`start_time`,`end_time`,`position`,`status`) values (#{name},#{content},#{img},#{places},#{places},#{sign_start_time},#{sign_end_time},#{start_time},#{end_time},#{positon},0)")
    int creatActive(String name, String content, String img,int places,Time sign_start_time, Time sign_end_time, Time start_time, Time end_time, String position);
    //获取活动详细信息
    @Select("select `name`,`content`,`img`,`sign_start_time`,`sign_end_time`,`start_time`,`end_time`,`position` from `active` where `Aid` = #{Aid}")
    Active getActiveByAid(int Aid);
    //查看活动剩余名额
    @Select("select `remainder_places` from `active` where `Aid` = #{Aid}")
    int getRemainderPlacesByAid(int Aid);
    //名额自减
    @Update("update `active` set `remainder_places` -= 1")
    int updateRemainderPlacesByAid(int Aid);
    //报名活动
    @Insert("insert into `active_applicant`(`Aid`,`uid`) values (#{Aid},#{uid})")
    int insertActiveApplicant(int Aid, int uid);
}
