package com.hj.springai.Service;

import com.hj.springai.entity.CommunityNotes;
import com.hj.springai.entity.MyNotes;
import com.hj.springai.mapper.MyNotesMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyNotesService {
    @Resource
    MyNotesMapper myNotesMapper;

    public int setNewMyNote(int uid,long MNid,String noteName,String img){
        return myNotesMapper.setNewMyNote(uid,MNid,noteName,img);
    }
    //获取我的笔记
    public List<MyNotes> listMyNotes(int uid) { return myNotesMapper.getMyNotes(uid);}
    //删除笔记
    public int deleteMyNotes(long MNid,int uid) {return myNotesMapper.deleteMyNotes(MNid,uid);}
    //保存笔记
    public int saveMyNotes(long MNid,String contentUrl) {return myNotesMapper.saveMyNotes(MNid,contentUrl);}
    //获取我喜欢的笔记
    public List<CommunityNotes> getLikeNotes(int uid){return myNotesMapper.getLikeNote(uid);}
    //根据id获取笔记内容
    public String getNoteContent(long MNid){return myNotesMapper.getNoteContent(MNid);}
}
