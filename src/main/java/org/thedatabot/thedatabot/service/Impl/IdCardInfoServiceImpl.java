package org.thedatabot.thedatabot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public String findIdPrefix6AndNameBYShanghai10(String searchKey) {
        String responseMessage;
        String[] split = searchKey.split(":");
        String idCardPrefix = split[0];
        String name = split[1];
        if(split.length != 2){
            return responseMessage = "分隔符错误";
        }
        if(idCardPrefix.length() > 6){
            return   responseMessage = "查询前缀大于六位";
        }
        LambdaQueryWrapper<IdCardInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.likeRight(IdCardInfo::getIdCardNumber, idCardPrefix)
                .eq(IdCardInfo::getName, name);
        // 创建分页对象，设置每页100条记录
        Page<IdCardInfo> page = new Page<>(1, 100);
        // 查询并获取结果
        Page<IdCardInfo> resultPage = idCardInfoMapper.selectPage(page, lambdaQueryWrapper);
        List<IdCardInfo> idCardInfos = resultPage.getRecords();
        if (idCardInfos == null) {
            responseMessage = "查询失败，请稍后再试。";
        } else if (idCardInfos.isEmpty()) {
            responseMessage = "未找到相关结果，请确认输入的身份证前缀。";
        } else if (idCardInfos.size() > 100) {
            responseMessage = "结果数量过多（>100），消息限制无法回显。请提供更详细的查询条件。";
        } else {
            StringBuilder result = new StringBuilder("查询结果如下：\n");
            for (IdCardInfo idCardInfo : idCardInfos) {
                result.append(idCardInfo.getName())
                        .append(" : ")
                        .append(idCardInfo.getIdCardNumber())
                        .append("\n");
            }
            result.append("总条数: ").append(resultPage.getTotal());
            responseMessage = result.toString();
        }
        return responseMessage;
    }
}




