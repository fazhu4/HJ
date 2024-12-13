package com.hj.springai.Controller;

import com.hj.springai.util.WenxinUtil;
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
    public void test(String str){
        System.out.println(WenxinUtil.Wenxin(str));
    }
}
