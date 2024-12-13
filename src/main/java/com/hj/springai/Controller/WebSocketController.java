package com.hj.springai.Controller;

import com.hj.springai.util.WenxinUtil;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @FileName WebSocketController
 * @Description
 * @Author fazhu
 * @date 2024-12-13
 **/
@Component
@ServerEndpoint("/server")
public class WebSocketController {
    static Logger log = LoggerFactory.getLogger(WebSocketController.class);

    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("连接成功");
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("连接关闭");
    }

    /**
     * 接收到消息
     *
     * @param session
     */
    @OnMessage
    public void onMsg(Session session, String msg)  {
        // 接收客户端发送的消息
        log.info("WebSocket Client: {}", msg);
        System.out.println( WenxinUtil.Wenxin(msg, session));

    }
}
