package com.hj.springai.entity;

import lombok.Data;

import java.sql.Time;

@Data
public class Active {
    int Aid; //活动id
    String name; //活动名
    String content; //活动内容
    String img; //活动图片
    int places; //名额
    int remainderPlaces; //剩余名额
    String signStartTime; //报名开始时间
    String signEndTime; //报名结束时间
    String StartTime; //开始时间
    String EndTime; //结束时间
    String position; //位置
    int status; //状态
}
