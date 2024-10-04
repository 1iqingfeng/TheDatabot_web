package org.thedatabot.thedatabot.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 示例响应类
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NapCatQQBaseResponse {
    private String status;
    private int retcode;
    private Object data;  // 根据实际情况定义类型
    private String message;
    private String wording;
    private Object echo;  // 根据实际情况定义类型
}