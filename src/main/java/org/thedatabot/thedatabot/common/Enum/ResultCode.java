package org.thedatabot.thedatabot.common.Enum;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, "Success"),
    FAILURE(500, "Failure"),
    NOT_FOUND(404, "Resource Not Found"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
