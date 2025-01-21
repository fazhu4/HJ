package com.hj.springai.entity;


import lombok.Data;

/**
 * @FileName User
 * @Description
 * @Author fazhu
 * @date 2024-12-11
 **/
@Data
public class User {
    private int uid;//用户id
    private String account;//用户账号
    private String password;//用户密码
    private int status;//用户状态
}
