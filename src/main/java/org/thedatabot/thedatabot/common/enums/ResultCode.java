package org.thedatabot.thedatabot.common.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, "请求成功"),
    FAILURE(500, "服务器错误"),
    NOT_FOUND(404, "不存在的路径"),
    UNAUTHORIZED(401, "未经授权"),
    FORBIDDEN(403, "禁止访问"),
    INVALID_ARGUMENT(400, "无效参数");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
