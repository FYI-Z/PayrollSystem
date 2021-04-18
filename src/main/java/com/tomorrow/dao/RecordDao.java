package com.tomorrow.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tomorrow.entity.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordDao extends BaseMapper<Record> {
}
