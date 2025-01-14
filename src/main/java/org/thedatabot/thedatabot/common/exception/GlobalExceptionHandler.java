package org.thedatabot.thedatabot.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.thedatabot.thedatabot.common.enums.ResultCode;
import org.thedatabot.thedatabot.common.result.Result;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception ex) {
        log.error("Exception{}",ex.getMessage());
        return Result.failure(ResultCode.FAILURE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Exception{}",ex.getMessage());
        return Result.failure(ResultCode.INVALID_ARGUMENT);
    }
    // 页面找不到的错误处理
    @ExceptionHandler(NoResourceFoundException.class)
    public Result<String> handleNoResourceFoundException(NoResourceFoundException ex) {
        log.error("Exception{}",ex.getMessage());
        return Result.failure(ResultCode.NOT_FOUND);
    }

}
