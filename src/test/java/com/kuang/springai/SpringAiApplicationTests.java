package com.kuang.springai;

import com.hj.springai.mapper.UserMapper;
import com.hj.springai.util.WenxinUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringAiApplicationTests {

    @Resource
    WenxinUtil wenxinUtil;
    @Resource
    UserMapper userMapper;


    @Test
    public void a() {
       System.out.println( userMapper.login("123","123"));
    }
}


