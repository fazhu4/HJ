package com.hj.springai.entity;

import lombok.Data;

import java.security.Timestamp;

@Data
public class CommunityNotes {
    private int CNid; //社区笔记id
    private int type; //笔记类型
    private String name; //笔记名
    private String img; //封面
    private int like; //喜欢数量
    private String label; //标签
    private Timestamp time; //发布时间
    private String status; //状态
}
