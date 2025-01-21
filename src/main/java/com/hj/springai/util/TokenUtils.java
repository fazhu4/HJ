package com.hj.springai.util;

/**
 * @FileName TokenUtils
 * @Description
 * @Author fazhu
 * @date 2024-12-10
 **/
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hj.springai.entity.User;
import com.hj.springai.mapper.UserMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class TokenUtils {
    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void init() {
        staticUserMapper =userMapper ;
    }

    /**
     * 获取token
     * @param uid 用户id
     * @param pw 用户密码
     * @return
     */
    public static String getToken(int uid,String pw) {
        String id=String.valueOf(uid);
        return JWT.create()
                .withAudience(id)//注入id
                .withExpiresAt(DateUtil.offsetDay(DateUtil.date(), 7))
                .sign(Algorithm.HMAC256(pw));//注入加密密码
    }


    /**
     * 获取当前用户信息
     * @return 用户信息
     */
    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization");
            if(StrUtil.isNotBlank(token)){
                int uid =Integer.parseInt(JWT.decode(token).getAudience().get(0)) ;
                User user= staticUserMapper.selectUserById(uid);
                return user;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        throw new RuntimeException();
    }
}


