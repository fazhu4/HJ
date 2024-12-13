package com.hj.springai.common;

/**
 * @FileName JWTnterceptors
 * @Description
 * @Author fazhu
 * @date 2024-12-10
 **/

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hj.springai.entity.User;
import com.hj.springai.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptors implements HandlerInterceptor {
    @Resource
    UserMapper userMapper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果有AuthAccess注解则跳过
        if(handler instanceof HandlerMethod){
            AuthAccess authAccess = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if(authAccess != null){
                return true;
            }
        }

        // 获取请求头中令牌
        String token = request.getHeader("token");
        if(StrUtil.isBlank(token)){
            throw new RuntimeException("令牌不能为空");
        }
        // 获取token中的用户信息
        String uid;
        try {
            uid = JWT.decode(token).getAudience().getFirst();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("令牌信息丢失");
        }
        // 根据token中的uid查询数据库用户信息
        User db_user = userMapper.selectUserById(Integer.parseInt(uid));
        if(db_user == null){
            throw new RuntimeException("用户不存在");
        }
        // 用户密码加签名验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(db_user.getPassword())).build();
        try {
            jwtVerifier.verify(token);//验证token
        } catch (JWTVerificationException e) {
            throw new RuntimeException();
        }
        return true;
    }
}
