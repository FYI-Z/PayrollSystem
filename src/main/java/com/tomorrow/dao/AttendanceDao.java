package com.tomorrow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tomorrow.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttendanceDao extends BaseMapper<Attendance> {

}
