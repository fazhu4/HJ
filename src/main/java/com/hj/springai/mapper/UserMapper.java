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
    @Select("select uid,account,password,status from user where uid =#{uid} ;")
    User selectUserById(int uid);

    //判断账号是否重复
    @Select("select exists(select uid from user where account =#{account});")
    Boolean findAccount(String account);

    //查询用户总数
    @Select("select count(*) from user")
    int userCount();

    //新增用户
    @Insert("insert into user (account,password,status) values (#{account},#{password},1) ")
    void insertUser(String account, String password);



}
