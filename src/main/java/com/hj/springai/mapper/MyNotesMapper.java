package com.hj.springai.mapper;

import com.hj.springai.entity.CommunityNotes;
import com.hj.springai.entity.MyNotes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyNotesMapper {
    //新建笔记
    @Insert("insert into `my_notes`(`uid`,`MNid`,`note_name`,`img`) values(#{uid},#{MNid},#{noteName},#{img})")
    int setNewMyNote(int uid, long MNid, String noteName,String img);
    //获取我的笔记
    @Select("select `note_name`,`img`,`mnid`,content_url from `my_notes` where `uid` = #{uid}")
    List<MyNotes> getMyNotes(@Param("uid") int uid);
    //删除我的笔记
    @Delete("delete from `my_notes` where `MNid` = #{CNid} and `uid` = #{uid}")
    int deleteMyNotes(@Param("CNid") long CNid, @Param("uid") int uid);
    //保存我的笔记
    @Update("update `my_notes` set `content_url` = #{contentUrl} where `MNid` = #{MNid}")
    int saveMyNotes(@Param("MNid") long MNid, @Param("contentUrl") String contentUrl);
    //获取我喜欢的笔记id
    @Select("select `CNid` from like_note where `uid` = #{uid}")
    List<CommunityNotes> getLikeNote(@Param("uid") int uid);
    //根据id获取内容
    @Select("select `content_url` from `my_notes` where `MNid` = #{MNid}")
    String getNoteContent(@Param("MNid") long MNid);
}
