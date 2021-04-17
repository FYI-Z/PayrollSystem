package com.tomorrow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tomorrow.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AttendanceDao extends BaseMapper<Attendance> {

}
