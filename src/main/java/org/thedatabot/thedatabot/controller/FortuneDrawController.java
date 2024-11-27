package org.thedatabot.thedatabot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thedatabot.thedatabot.entity.pojo.GuanyinLingQian;
import org.thedatabot.thedatabot.enums.ResponseCode;
import org.thedatabot.thedatabot.response.Response;
import org.thedatabot.thedatabot.response.ResponseMessage;
import org.thedatabot.thedatabot.service.GuanyinLingQianService;

import java.util.Random;

@RestController
@RequestMapping("fortunedraw")
@AllArgsConstructor
public class FortuneDrawController {

    private final GuanyinLingQianService guanyinLingQianService;

    // 随机获得一个抽签内容
    @GetMapping("guanyin")
    public Response<GuanyinLingQian> getAll() {
        Random random = new Random();
        int id = random.nextInt(100) + 1; // 生成 1 到 100 之间的随机数
        GuanyinLingQian guanyinLingQian = guanyinLingQianService.getById(id);

        // 如果未找到签文，返回错误响应
        if (guanyinLingQian == null) {
            return Response.error(ResponseMessage.NOT_FOUND_SIGN, ResponseCode.NOT_FOUND);
        }

        // 返回成功响应
        return Response.success(guanyinLingQian);
    }
}
