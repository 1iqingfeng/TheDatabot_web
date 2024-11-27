package org.thedatabot.thedatabot.response;

/**
 * 存储 API 响应中的常见消息常量。
 */
public final class ResponseMessage {

    public static final String NOT_FOUND_SIGN = "未找到对应的签文";
    public static final String INTERNAL_SERVER_ERROR = "服务器内部错误";
    public static final String UNAUTHORIZED = "未经授权";
    
    // 防止外部实例化该类
    private ResponseMessage() {
        throw new UnsupportedOperationException("Cannot instantiate constants class");
    }
}
