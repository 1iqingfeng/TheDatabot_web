package org.thedatabot.thedatabot.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.thedatabot.thedatabot.enums.ResponseCode;

/**
 * 通用响应类，用于标准化返回的 API 数据格式。
 *
 * @param <T> 响应数据的类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    /**
     * 响应消息，通常为操作结果的描述，例如成功、错误等。
     */

    private String message;

    /**
     * HTTP 状态码，通常为 200（成功）、404（未找到）、500（服务器错误）等。
     */

    private int statusCode;

    /**
     * 响应的数据部分，可以是任意类型的数据，通常是请求的返回数据。
     */

    private T data;

    /**
     * 成功响应构造函数，设置默认的成功消息和状态码 200。
     *
     * @param data 响应的数据
     */
    public Response(T data) {
        this.message = "success";
        this.statusCode = 200;
        this.data = data;
    }

    /**
     * 错误响应构造函数，设置错误消息和自定义的状态码。
     *
     * @param message 错误消息
     * @param statusCode 错误的状态码
     */
    public Response(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = null;
    }

    /**
     * 成功响应的静态方法，便于快速创建带数据的成功响应。
     *
     * @param <T> 响应的数据类型
     * @param data 响应的数据
     * @return 包装成功响应的 Response 对象
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(data);
    }

    /**
     * 错误响应的静态方法，便于快速创建带错误信息和状态码的错误响应。
     *
     * @param message 错误消息
     * @param StatusCode 错误状态码
     * @return 包装错误响应的 Response 对象
     */
    public static <T> Response<T> error(String message, ResponseCode StatusCode) {
        return new Response<>(message, StatusCode.getCode());
    }
}
