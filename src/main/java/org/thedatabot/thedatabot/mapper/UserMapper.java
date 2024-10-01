package org.thedatabot.thedatabot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.thedatabot.thedatabot.entity.pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
