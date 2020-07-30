package com.ng.service.mapper;

import com.ng.service.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author guoxing
 * @date 2020/7/30
 * @description 描述
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM User WHERE id = #{id}")
    User queryUser(long id);

}
