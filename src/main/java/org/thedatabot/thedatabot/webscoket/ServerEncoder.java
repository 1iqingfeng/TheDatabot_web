package org.thedatabot.thedatabot.webscoket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

/**
 * WebSocket 通用对象编码器，将任意类型的对象编码为 JSON 格式。
 *
 * @param <T> 需要编码的对象类型
 */
public class ServerEncoder<T> implements Encoder.Text<T> {

    // 使用静态实例来复用 JsonMapper，避免重复创建
    private static final JsonMapper jsonMapper = new JsonMapper();

    /**
     * 将指定类型的对象编码为 JSON 字符串格式
     *
     * @param responseMessage 需要进行编码的对象
     * @return 编码后的 JSON 字符串
     * @throws EncodeException 如果编码失败，则抛出该异常
     */
    @Override
    public String encode(T responseMessage) throws EncodeException {
        if (responseMessage == null) {
            throw new EncodeException(responseMessage, "不能编码空对象");
        }

        try {
            return jsonMapper.writeValueAsString(responseMessage);
        } catch (JsonProcessingException e) {
            // 记录日志并抛出 EncodeException
            throw new EncodeException(responseMessage, "对象编码为 JSON 字符串时发生错误：" + e.getMessage(), e);
        }
    }

    /**
     * 初始化方法（可选）。在编码器创建时执行一些初始化操作。
     */
    @Override
    public void init(EndpointConfig config) {
        // 可以根据需求进行编码器的初始化操作
    }

    /**
     * 销毁方法（可选）。在编码器关闭时释放资源。
     */
    @Override
    public void destroy() {
        // 此处没有需要销毁的资源，可以留空
    }
}
