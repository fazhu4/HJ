package com.hj.springai.mapper;

import com.hj.springai.entity.CommunityNotes;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @FileName CommunityMapper
 * @author yrpyy
 * @Data 2024-12-18
 * **/
@Mapper
public interface CommunityMapper {
    //
//    @Select("select `CNid` from `community_notes` where like > #{};")
//    int[] getCommunityNotesByLike(String like);
    //喜欢笔记
    @Update("update `community_notes` set `uid` = #{uid},`CNid` = #{CNid};")
    int setLikeNote(int uid, int CNid);
    //删除喜欢笔记
    @Delete("delete from `community_notes` where `uid` = #{uid} and `CNid` = #{CNid};")
    int deleteLikeNote(int uid, int CNid);
    //喜欢自增
    @Update("update `community_notes` set `like` += 1 where `CNid` = #{CNid};")
    int LikeIncrease(int CNid);
    //喜欢自减
    @Update("update `community_notes` set `like` -= 1 where `CNid` = #{CNid};")
    int LikeDecrease(int CNid);
    //根据id返回封面，名字
    @Select("select `img`,`note_name` form `my_notes` where `CNid` = #{CNid};")
    CommunityNotes getCommunityNotes(int CNid);
}
