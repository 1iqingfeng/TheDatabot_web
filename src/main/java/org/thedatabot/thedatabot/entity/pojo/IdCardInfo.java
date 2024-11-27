package org.thedatabot.thedatabot.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName id_card_info
 */
@TableName(value ="id_card_info")
@Data
public class IdCardInfo implements Serializable {
    private Integer id;

    private String idCardNumber;

    private String name;

    private static final long serialVersionUID = 1L;
}