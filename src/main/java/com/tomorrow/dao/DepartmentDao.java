package com.tomorrow.dao;

import com.tomorrow.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentDao {

    @Select("select * from department")
    List<Department> findAll();
}
