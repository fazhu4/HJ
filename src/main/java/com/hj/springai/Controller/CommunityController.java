package com.hj.springai.Controller;

import com.hj.springai.Service.CommunityService;
import com.hj.springai.common.AuthAccess;
import com.hj.springai.common.Result;
import com.hj.springai.util.FileUtils;
import com.hj.springai.util.TokenUtils;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @FileName CommunityController
 * @Description
 * @Author yrpyy
 * @date 2024-12-15
 **/
@RestController
@RequestMapping("/community")
public class CommunityController {
    @Resource
    private CommunityService communityService;


    //笔记上传
    @AuthAccess
    @PostMapping("/noteUpload")
    public Result noteUpload(@Param("file") MultipartFile file) {
        String msg;
        FileUtils fileUtils = new FileUtils();
        String UPLOAD_DIR = System.getProperty("user.dir") + "\\src\\main\\resources\\information\\";
        try {
            msg = fileUtils.upLoad(file,UPLOAD_DIR);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success(msg);
    }

    //获取热门笔记
    @GetMapping("/hotNotes")
    public Result getHotNotes(@Param("typeId")int typeId) {
        try {
            communityService.getHotNotes(typeId);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //获取笔记列表
    @GetMapping("/getList")
    public Result getList(@Param("type") int type) {
        return Result.success("success");
    }

    //收藏/喜欢 笔记
    @GetMapping("/like")
    public Result LikeNote(@Param("CNid") int CNid, @Param("type") int type) {
        int uid = TokenUtils.getCurrentUser().getUid();
        if (type == 1) {
            communityService.setLikeNote(uid, CNid);
            communityService.likeIncrease(CNid);
            return Result.success();
        } else if (type == 0) {
            communityService.deleteLikeNote(uid, CNid);
            communityService.likeDecrease(CNid);
            return Result.success();
        }
        return Result.error("错误type");
    }

}
