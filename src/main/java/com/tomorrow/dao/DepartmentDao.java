package com.tomorrow.dao;

import com.tomorrow.entity.Department;
import org.apache.ibatis.annotations.Insert;
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

    @Select("select * from department where is_del=0")
    List<Department> listEchart();

    @Update("<script>" +
            "update department set is_del=1 where departmentid=#{departmentid}"+
            "</script>")
    int del(@Param("departmentid") String departmentid);

    @Select("select distinct * from department where is_del=0 and locate(#{name},name) and locate(#{operator},operator)")
    List<Department> findMsg(@Param("name") String name,@Param("operator")String operator);

    @Select("select distinct * from department where is_del=0 and locate(#{name},name)")
    List<Department> findMsgByName(@Param("name") String name);

    @Select("select distinct * from department where is_del=0 and name=#{name}")
    List<Department> findMsgAccByName(@Param("name") String name);

    @Select("select distinct * from department where is_del=0 and locate(#{operator},operator)")
    List<Department> findMsgByOper(@Param("operator") String operator);

    @Update("insert into department values(#{departmentid},#{name},#{number},#{operator},#{createtime},0,#{remark})")
    int add(@Param("departmentid") String departmentid,
            @Param("name") String name,
            @Param("number") int number,
            @Param("operator") String operator,
            @Param("createtime") String createtime,
            @Param("remark") String remark);

    @Update("update department set name=#{name},remark=#{remark},createtime=#{createtime} where name=#{Oname}")
    int update(@Param("name")String name, @Param("Oname") String Oname,
               @Param("remark") String remark,@Param("createtime")String createtime);
}
