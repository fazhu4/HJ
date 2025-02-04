package com.hj.springai.Controller;

import com.hj.springai.Service.MyNotesService;
import com.hj.springai.common.AuthAccess;
import com.hj.springai.common.Result;
import com.hj.springai.entity.CommunityNotes;
import com.hj.springai.entity.MyNotes;
import com.hj.springai.util.FileUtils;
import com.hj.springai.util.TokenUtils;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/myNote")
public class MyNotesController {
    @Resource
    private MyNotesService myNotesService;

    //设置新笔记
    @AuthAccess
    @GetMapping("/setNewNote")
    public Result setNewNote(@Param("noteName")String noteName,@Param("img")String img) {
        int uid = TokenUtils.getCurrentUser().getUid();
        try {
            int x = myNotesService.setNewMyNote(uid, FileUtils.getFileId(),noteName,img);
            if(x>0){
                return Result.success("创建成功");
            }else {
                return Result.error("创建失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取我的笔记基本信息
    @GetMapping("/listMyNotes")
    public Result listMyNote() {
        int uid = TokenUtils.getCurrentUser().getUid();
        try {
            List<MyNotes> x = myNotesService.listMyNotes(uid);
            return Result.success(x);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //删除笔记
    @GetMapping("/delete")
    public Result deleteMyNote(@Param("MNid")long MNid) {
        int uid = TokenUtils.getCurrentUser().getUid();
        myNotesService.deleteMyNotes(MNid,uid);
        return Result.success();
    }

    //保存笔记
    @GetMapping("/save")
    public Result saveMyNote(@Param("MNid")long MNid,@Param("contentUrl")String contentUrl){
        try {
            int x = myNotesService.saveMyNotes(MNid,contentUrl);
            if(x>0){
                return Result.success("保存成功");
            }else {
                return Result.error("保存失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //获取我喜欢的笔记
    @GetMapping("/listLikeNotes")
    public Result listLikeNote() {
        int uid = TokenUtils.getCurrentUser().getUid();
        try {
            List<CommunityNotes> x = myNotesService.getLikeNotes(uid);
            if (x != null){
                return Result.success(x);
            }else {
                return Result.success("您还没有喜欢的笔记");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //获取单个笔记内容
    @GetMapping("/getContent")
    public Result getContent(@Param("MNid")long MNid) {
        String content = null;
        try {
            content = myNotesService.getNoteContent(MNid);
            if(content!=null){
                return Result.success(content);
            }else {
                return Result.success("该笔记没有内容");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

}
