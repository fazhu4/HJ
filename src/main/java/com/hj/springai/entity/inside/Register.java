package com.hj.springai.entity.inside;


import lombok.Data;


/**
 * @FileName Register
 * @Description
 * @Author fazhu
 * @date 2024-12-13
 **/

@Data
public class Register {
    private String avatar;//用户头像
    private String name;//用户姓名
    private String account;//用户账号
    private String password;//用户密码
}
