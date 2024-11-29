package org.thedatabot.thedatabot.common.utils;
import org.thedatabot.thedatabot.common.enums.ResultCode;
import org.thedatabot.thedatabot.common.result.Result;

public class ResponseUtil {

    // 构建成功响应
    public static <T> Result<T> buildSuccess(T data) {
        return Result.success(data);
    }

    // 构建自定义成功消息的响应
    public static <T> Result<T> buildSuccess(String message, T data) {
        return Result.success(message, data);
    }

    // 构建失败响应
    public static Result<String> buildFailure(ResultCode code, String message) {
        return Result.failure(code, message);
    }
}
