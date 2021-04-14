package com.tomorrow.dao;

import com.tomorrow.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentDao {

    @Select("select * from department where is_del=0")
    List<Department> findAll();
    @Update("<script>" +
            "update department set is_del=1 where departmentid=#{departmentid}"+
            "</script>")
    int del(@Param("departmentid") String departmentid);
}
