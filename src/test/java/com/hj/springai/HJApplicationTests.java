package com.hj.springai;

import com.hj.springai.Service.TimeService;
import com.hj.springai.mapper.UserMapper;
import com.hj.springai.util.WenxinUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class HJApplicationTests {

    @Resource
    WenxinUtil wenxinUtil;
    @Resource
    UserMapper userMapper;
    @Resource
    TimeService timeService;


    @Test
    public void a() {
       System.out.println( userMapper.login("123","123"));
    }

    @Test
    public void b(){
        String s="洗澡";
        System.out.printf(String.valueOf(timeService.strToArr(s)));
    }
}


