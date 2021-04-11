package com.tomorrow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tomorrow.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
