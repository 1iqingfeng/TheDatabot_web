package org.thedatabot.thedatabot.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.thedatabot.thedatabot.entity.dto.MessageEvent;

import java.io.IOException;

public class SingleMessageDeserializer extends JsonDeserializer<MessageEvent.Message> {

    @Override
    public MessageEvent.Message deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        
        // 如果是数组，提取第一个元素
        if (node.isArray() && !node.isEmpty()) {
            return parser.getCodec().treeToValue(node.get(0), MessageEvent.Message.class);
        }
        
        // 如果不是数组，直接解析为单个对象
        return parser.getCodec().treeToValue(node, MessageEvent.Message.class);
    }
}
