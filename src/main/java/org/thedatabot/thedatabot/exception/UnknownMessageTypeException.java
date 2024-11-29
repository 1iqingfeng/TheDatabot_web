package org.thedatabot.thedatabot.exception;

import org.thedatabot.thedatabot.common.enums.Bot.BotErrorType;

public class UnknownMessageTypeException extends RuntimeException {
    public UnknownMessageTypeException(BotErrorType errorType) {
        super(errorType.getMessage());
    }

    public UnknownMessageTypeException(BotErrorType errorType, Throwable cause) {
        super(errorType.getMessage(), cause);
    }
}
