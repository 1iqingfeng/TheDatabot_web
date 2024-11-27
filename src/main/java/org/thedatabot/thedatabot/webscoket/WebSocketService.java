package org.thedatabot.thedatabot.webscoket;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.thedatabot.thedatabot.interceptor.MessageInterceptor;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{userName}", encoders = {ServerEncoder.class})
@Component
@Slf4j
@NoArgsConstructor
public class WebSocketService {
    private static ApplicationContext applicationContext;

    @Autowired
    public WebSocketService(ApplicationContext applicationContext) {
        WebSocketService.applicationContext = applicationContext;
    }

    private MessageInterceptor messageInterceptor() {
        return applicationContext.getBean(MessageInterceptor.class);
    }


    // 存储客户端连接的 WebSocket 会话，key 为用户名
    private static final ConcurrentHashMap<String, WebSocketClient> webSocketMap = new ConcurrentHashMap<>();

    // 当前会话的 session
    private Session session;

    // 当前连接用户的用户名
    private String userName;

    /**
     * 客户端与服务端连接成功时调用
     *
     * @param session 当前会话 session
     * @param userName 客户端传入的用户名
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName) {
        this.session = session;
        this.userName = userName;
        // 创建客户端信息对象并保存到 webSocketMap 中
        WebSocketClient client = new WebSocketClient(session,session.getRequestURI().toString());
        webSocketMap.put(userName, client);
        log.info("用户 [{}] 连接成功，URI: [{}]", userName, session.getRequestURI());
    }
    /**
     * 接收客户端发送的消息时调用
     *
     * @param session 当前会话 session
     * @param message 客户端发送的消息内容
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        // log.info("收到用户 [{}] 的消息：{}", this.userName, message);
        try {
            messageInterceptor().intercept(message);
        } catch (Exception e) {
            log.error("消息处理失败", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * 客户端与服务端连接关闭时调用
     */

    @OnClose
    public void onClose() {
        // 连接关闭时移除当前用户的 WebSocket 连接
        webSocketMap.remove(this.userName);
        log.info("用户 [{}] 的连接已关闭", userName);
    }

    /**
     * 连接发生异常时调用
     *
     * @param error 异常对象
     * @param session 当前会话 session
     */

    @OnError
    public void onError(Throwable error, Session session) {
        log.error("用户 [{}] 发生异常，Session ID: [{}]", this.userName, session.getId(), error);
    }

    /**
     * 向指定客户端发送对象消息（使用泛型）
     *
     * @param userName 目标用户名
     * @param message  发送的对象消息
     * @param <T>      消息的类型
     */
    public static <T> void sendVo(String userName, T message) {
        WebSocketClient webSocketClient = webSocketMap.get(userName);
        if (webSocketClient != null) {
            try {
                webSocketClient.getSession().getBasicRemote().sendObject(message);
                log.info("成功向用户 [{}] 发送对象消息：{}", userName, message);
            } catch (IOException | EncodeException e) {
                log.error("向用户 [{}] 发送对象消息失败", userName, e);
                throw new RuntimeException("消息发送失败: " + e.getMessage());
            }
        } else {
            log.warn("用户 [{}] 的连接不存在，消息发送失败", userName);
        }
    }


    /**
     * 向指定客户端发送文本消息
     *
     * @param userName 目标用户名
     * @param message 发送的文本消息
     */
    public static void sendMessage(String userName, String message) {
        WebSocketClient webSocketClient = webSocketMap.get(userName);
        if (webSocketClient != null) {
            try {
                webSocketClient.getSession().getBasicRemote().sendText(message);
                log.info("成功向用户 [{}] 发送文本消息：{}", userName, message);
            } catch (IOException e) {
                log.error("向用户 [{}] 发送文本消息失败", userName, e);
                throw new RuntimeException("消息发送失败: " + e.getMessage());
            }
        } else {
            log.warn("用户 [{}] 的连接不存在，消息发送失败", userName);
        }
    }
}
