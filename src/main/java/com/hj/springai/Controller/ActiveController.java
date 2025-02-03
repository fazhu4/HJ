package com.hj.springai.Controller;

import com.hj.springai.Service.ActiveService;
import com.hj.springai.common.AuthAccess;
import com.hj.springai.common.Result;
import com.hj.springai.entity.Active;
import com.hj.springai.util.ActivityTaskExecutor;
import com.hj.springai.util.TokenUtils;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/active")
public class ActiveController {
    @Resource
    ActivityTaskExecutor activityTaskExecutor;
    @Resource
    ActiveService activeService;

    @GetMapping("/creatActive")
    public Result creatActive(@Param("active") Active active) {
        try {
            activeService.creatActive(active.getName(),active.getContent(),active.getImg(),active.getPlaces(),active.getSignStartTime(),active.getSignEndTime(),active.getStartTime(),active.getEndTime(),active.getPosition());
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/getActiveList")
    public Result getActiveList() {
        try {
            List<Active> activeList = activeService.getActiveList();
            return Result.success(activeList);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/getActiveInfo")
    public Result getActiveInfo(@Param("Aid")int Aid) {
        try {
            Active active = activeService.getActiveByAid(Aid);
            return Result.success(active);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/applicant")
    public Result applicantActive(@Param("Aid")int Aid){
        try {
            if (activeService.getRemainderPlaces(Aid) > 0) {
                activeService.updateRemainderPlacesByAid(Aid);
                return Result.success("报名成功");
            }else {
                return Result.success("无名额了");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
