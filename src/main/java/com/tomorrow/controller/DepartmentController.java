package com.tomorrow.controller;

import cn.hutool.core.collection.SpliteratorUtil;
import com.tomorrow.entity.Department;
import com.tomorrow.service.DepartmentService;
import com.tomorrow.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/findAll")
    public List<Department> list(int limit){
        return departmentService.findAll(limit);
    }


    @RequestMapping("/del")
    public ReturnResult del(Department department){
        return departmentService.del(department.getDepartmentid());
    }

    @RequestMapping(value = "/findMsg",produces = "application/json;charset=UTF-8")
    public List<Department> findMsg(@RequestParam  String name,String operator){
        return departmentService.findResult(name,operator);

    }
    @RequestMapping(value = "/add",produces = "application/json;charset=UTF-8")
    public ReturnResult add( Department department){
        return departmentService.add(department);
    }

    @RequestMapping("/update")
    public List<Department> update(@RequestBody List<Department>departments){
        for(Department depart:departments){
            del(depart);
        }
        return departments;
    }

    @RequestMapping("/echart")
    public List<Department> listEchart(){
        return departmentService.listEchart();
    }


}
