package com.hj.springai.mapper;


import com.hj.springai.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @FileName UserMapper
 * @Description
 * @Author fazhu
 * @date 2024-12-11
 **/
@Mapper
public interface UserMapper {
    //判断账密是否正确
    @Select("select uid from user where account =#{account} and password= #{password} ;")
    Integer login(String account, String password);

    //根据id查询用户信息
    @Select("select uid,avatar,name,account,status from user where uid =#{uid} ;")
    User selectUserById(int uid);

    //新增用户
    @Insert("insert into user (avatar,name,account,password) values (#{avatar},#{name},#{account},#{password}) ")
    void insertUser(String avatar, String name, String account, String password);

    //查询用户总数
    @Select("select count(*) from user")
    int userCount();

}
