package org.thedatabot.thedatabot.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("guanyinlingqian")
public class GuanyinLingQian {
    private Integer id;
    private String title;
    private String poetry;
    private String isGood;
    private String text;
    private String interpretation;
}