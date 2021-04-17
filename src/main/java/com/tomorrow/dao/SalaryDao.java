package com.tomorrow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tomorrow.entity.Salary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalaryDao extends BaseMapper<Salary> {
}
