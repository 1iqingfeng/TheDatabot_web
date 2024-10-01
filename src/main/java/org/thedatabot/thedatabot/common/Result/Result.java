package org.thedatabot.thedatabot.common.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.thedatabot.thedatabot.common.Enum.ResultCode;

import java.io.Serializable;

@Data
@NoArgsConstructor  // 生成无参构造方法
@AllArgsConstructor  // 生成包含所有属性的全参构造方法
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    // 枚举式成功响应
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    // 自定义消息的成功响应
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    // 枚举式失败响应
    public static <T> Result<T> failure(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    // 自定义消息的失败响应
    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message, null);
    }
}
