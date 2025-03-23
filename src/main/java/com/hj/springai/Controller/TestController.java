package com.hj.springai.Controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName TestController
 * @Description
 * @Author fazhu
 * @date 2024-12-13
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public void test(@Param("str") String str){
        System.out.println(str);
    }
}
