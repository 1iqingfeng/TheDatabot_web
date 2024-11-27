package org.thedatabot.thedatabot.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thedatabot.thedatabot.entity.pojo.GuanyinLingQian;
import org.thedatabot.thedatabot.service.GuanyinLingQianService;

import java.util.Random;

@RestController
@RequestMapping("/guanyin-lingqian")
@AllArgsConstructor
public class GuanyinLingQianController {
    private final GuanyinLingQianService guanyinLingQianService;

    // 随机获得一个抽签内容
    @GetMapping
    public GuanyinLingQian getAll() {
        Random random = new Random();
        int id = random.nextInt(100) + 1; // 生成 1 到 100 之间的随机数
        return guanyinLingQianService.getById(id);

    }
}