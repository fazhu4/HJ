package com.hj.springai.Service;

import com.hj.springai.entity.Active;
import com.hj.springai.mapper.ActiveMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class ActiveService {
    @Resource
    ActiveMapper activeMapper;
    //创建活动
    public void creatActive(String name, String content, String img, int places,Time sign_start_time, Time sign_end_time, Time start_time, Time end_time, String position) {activeMapper.creatActive(name,content,img,places,sign_start_time,sign_end_time,start_time,end_time,position);}
    //获取未开始的活动列表
    public List<Active> getActiveList() {return activeMapper.getActiveNoStart();}
    //获取已开始的活动列表
    //获取活动详细信息
    public Active getActiveByAid(int Aid){return activeMapper.getActiveByAid(Aid);}
    //查看剩余名额
    public int getRemainderPlaces(int Aid){return activeMapper.getRemainderPlacesByAid(Aid);}
    //名额自减
    public void updateRemainderPlacesByAid(int Aid){activeMapper.updateRemainderPlacesByAid(Aid);}
    //插入报名表
    public void InsertActiveApplicant(int Aid,int uid){activeMapper.insertActiveApplicant(Aid,uid);}
}
