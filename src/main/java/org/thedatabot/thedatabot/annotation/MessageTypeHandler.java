package org.thedatabot.thedatabot.annotation;

import org.thedatabot.thedatabot.common.enums.Bot.MessageType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MessageTypeHandler {
    MessageType value(); // 指定处理器处理的消息类型
}
