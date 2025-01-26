package com.hj.springai.Service;

import com.hj.springai.entity.CommunityNotes;
import com.hj.springai.mapper.CommunityMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Filename CommunityService
 * @author yrpyy
 * @Data 2024-12-18
 * **/
@Service
public class CommunityService {
    @Resource
    CommunityMapper communityMapper;

    //喜欢笔记
    public int setLikeNote(int uid,int CNid){return communityMapper.setLikeNote(uid,CNid);}
    //删除喜欢笔记
    public int deleteLikeNote(int uid,int CNid){return communityMapper.deleteLikeNote(uid,CNid);}
    //喜爱数加1
    public int likeIncrease(int CNid){return communityMapper.LikeIncrease(CNid);}
    //喜爱数减1
    public int likeDecrease(int CNid){return communityMapper.LikeDecrease(CNid);}
    //根据社区笔记id返回封面，名字
    public CommunityNotes getCommunityNotes(int CNid){return communityMapper.getCommunityNotes(CNid);}
}
