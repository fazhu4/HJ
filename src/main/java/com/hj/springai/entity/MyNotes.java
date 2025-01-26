package com.hj.springai.entity;

import lombok.Data;

@Data
public class MyNotes {
    int uid; //作者Id
    long MNid; //笔记id
    String note_name; //笔记名
    String content_url;  //内容url
    String img; //封面地址
}
