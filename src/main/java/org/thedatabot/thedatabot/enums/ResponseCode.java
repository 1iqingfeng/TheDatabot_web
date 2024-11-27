package org.thedatabot.thedatabot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应码枚举，定义了所有的 HTTP 状态码及对应的消息。
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS(200, "成功"),
    ERROR(500, "服务器错误"),
    NOT_FOUND(404, "未找到资源"),
    BAD_REQUEST(400, "错误的请求"),
    UNAUTHORIZED(401, "未经授权"),
    FORBIDDEN(403, "禁止访问");

    /**
     * 响应码
     */
    private final int code;

    /**
     * 响应消息
     */
    private final String message;

    /**
     * 根据响应码获取响应消息
     * @param code 响应码
     * @return 响应消息
     */
    public static String getMessageByCode(int code) {
        for (ResponseCode responseCode : values()) {
            if (responseCode.getCode() == code) {
                return responseCode.getMessage();
            }
        }
        return "未知错误";
    }
}
