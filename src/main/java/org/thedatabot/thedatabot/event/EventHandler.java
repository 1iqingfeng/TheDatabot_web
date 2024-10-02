package org.thedatabot.thedatabot.event;

public interface EventHandler {
    void handle(String message) throws Exception;
}
