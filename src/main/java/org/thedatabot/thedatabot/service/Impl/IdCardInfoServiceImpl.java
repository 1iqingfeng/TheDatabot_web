package org.thedatabot.thedatabot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thedatabot.thedatabot.entity.pojo.IdCardInfo;
import org.thedatabot.thedatabot.mapper.IdCardInfoMapper;
import org.thedatabot.thedatabot.service.IdCardInfoService;

import java.util.List;

/**
* @author admin
* @description 针对表【id_card_info】的数据库操作Service实现
* @createDate 2024-10-04 13:38:04
*/
@Service
public class IdCardInfoServiceImpl extends ServiceImpl<IdCardInfoMapper, IdCardInfo> implements IdCardInfoService{
    @Autowired
    private IdCardInfoMapper idCardInfoMapper;

    @Override
    public List<IdCardInfo> findIdPrefix6AndNameBYShanghai10(String rawMessage) {
        String[] split = rawMessage.split(":");
        LambdaQueryWrapper<IdCardInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.likeRight(IdCardInfo::getIdCardNumber,split[0]).eq(IdCardInfo::getName,split[1]);
        return idCardInfoMapper.selectList(lambdaQueryWrapper);
    }
}




