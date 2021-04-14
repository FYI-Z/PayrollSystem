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

    @Select("select * from department where is_del=0 limit #{limit}")
    List<Department> findAll(@Param("limit") int limit);

    @Update("<script>" +
            "update department set is_del=1 where departmentid=#{departmentid}"+
            "</script>")
    int del(@Param("departmentid") String departmentid);

//    @Select("<script>" +
//            "select *from department where "+
//            "<if test='department!=null'>departmentid=#{departmentid} </if>" +
//            "<if test='name!=null' and department!=null'>and </if>" +
//            "<if test='name!=null'>name=#{name}</if>" +
//            "<if test='name!=null' and department!=null and operator!=null'>and</if>" +
//            "<if test='operator!=null'>operator=#{operator}</if>" +
//            "</script>")
    @Select("select * from department where is_del=0 and name=#{name}")
    List<Department> findMsg(@Param("name") String name);
}
