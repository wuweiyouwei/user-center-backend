package com.wv.userhome.mapper;

import com.wv.userhome.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 21192
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2022-11-14 20:39:59
* @Entity com.wv.userhome.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




