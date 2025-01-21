package com.hj.springai.Controller;


import com.hj.springai.Service.UserService;
import com.hj.springai.common.Result;
import com.hj.springai.config.GlobalConfiguration;
import com.hj.springai.entity.inside.Login;
import com.hj.springai.entity.inside.Register;
import com.hj.springai.mapper.UserMapper;
import com.hj.springai.util.EntityUtils;
import com.hj.springai.util.TokenUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.hj.springai.common.Result.success;

/**
 * @FileName UserController
 * @Description
 * @Author fazhu
 * @date 2024-12-12
 **/
@Slf4j(topic = "UserController")
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Resource
    GlobalConfiguration globalConfiguration;

    /**
     * 用户登录
     *
     * @param login 用户登录信息
     * @return token
     */
    @PostMapping("/login")
    public Result login(@RequestBody Login login) {
        try {
            if (EntityUtils.YorN_null(login)) {
                int user_id = 0;
                try {
                    user_id = userMapper.login(login.getAccount(), login.getPassword());
                } catch (Exception e) {
                    return Result.error("账号密码错误");
                }
                if (userMapper.selectUserById(user_id).getStatus() > 0) {
                    return success("登录成功", TokenUtils.getToken(user_id, login.getPassword()));
                } else {
                    return Result.error("该用户已被冻结");
                }
            } else {
                return Result.error("账号密码不能留空");
            }
        } catch (Exception e) {
            return Result.error(500, "服务器异常");
        }
    }

    /**
     * 用户注册
     * @param register
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody Register register) {
        try {
            if ((register.getAccount() != null || register.getAccount() != "" )&& (register.getPassword()!=null || register.getPassword()!="")) {
                if(userMapper.findAccount(register.getAccount())){//查看账号是否重复
                    return Result.error("账号重复啦");
                }
                userMapper.insertUser( register.getAccount(), register.getPassword());
                return success("注册成功",register);
            } else {
                return Result.error("账号密码不能留空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(500, "服务器异常");
        }
    }
}
