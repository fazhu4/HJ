package com.hj.springai.Controller;

import com.hj.springai.common.Result;
import com.hj.springai.util.TokenUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @FileName ClockController
 * @Description
 * @Author fazhu
 * @date 2024-12-20
 **/
@Controller
@Component("/clock")
public class ClockController {

    /**
     * 签到
     * @return 签到结果
     */
    @PostMapping("/attendance")
    public Result attendance(){
        try {
            int uid=TokenUtils.getCurrentUser().getUid();
        } catch (Exception e) {
            Result.error("Token失效，请重新登录");
        }
        return Result.success();
    }
}
