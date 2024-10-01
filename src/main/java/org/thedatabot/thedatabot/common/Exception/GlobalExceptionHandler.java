package org.thedatabot.thedatabot.common.Exception;
import org.thedatabot.thedatabot.common.Result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception ex) {
        return Result.failure(500, "Internal Server Error: " + ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return Result.failure(400, "Invalid Argument: " + ex.getMessage());
    }
}
