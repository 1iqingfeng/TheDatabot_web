package org.thedatabot.thedatabot.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HeartbeatEvent {
    
    @JsonProperty("time")
    private long time;

    @JsonProperty("self_id")
    private long selfId;

    @JsonProperty("post_type")
    private String postType;

    @JsonProperty("meta_event_type")
    private String metaEventType;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("interval")
    private int interval;

    @Data // 这个注解会自动生成 getter、setter 等方法
    public static class Status {
        @JsonProperty("online")
        private boolean online;

        @JsonProperty("good")
        private boolean good;
    }
}
