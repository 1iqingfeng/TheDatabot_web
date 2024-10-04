package org.thedatabot.thedatabot.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.thedatabot.thedatabot.entity.pojo.IdCardInfo;

import java.util.List;

/**
* @author admin
* @description 针对表【id_card_info】的数据库操作Service
* @createDate 2024-10-04 13:38:04
*/
public interface IdCardInfoService extends IService<IdCardInfo> {

    @DS("shanghai10")
    List<IdCardInfo> findIdPrefix6AndNameBYShanghai10(String rawMessage);
}
