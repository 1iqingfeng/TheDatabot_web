package org.thedatabot.thedatabot.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data // 这个注解会自动生成 getter、setter、toString、equals 和 hashCode 方法
public class LifecycleEvent {
    
    @JsonProperty("time")
    private long time;

    @JsonProperty("self_id")
    private long selfId;

    @JsonProperty("post_type")
    private String postType;

    @JsonProperty("meta_event_type")
    private String metaEventType;

    @JsonProperty("sub_type")
    private String subType;
}
