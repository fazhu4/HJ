package com.hj.springai.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Wenxin {
    private Map<String, ChatConfig> wenxin;

    @Data
    public static class ChatConfig {
        private String url;
        private String method;
        private Map<String, String> headers;
        private Map<String, String> params;

    }
}
