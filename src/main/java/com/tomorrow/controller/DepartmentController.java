package com.tomorrow.controller;

import cn.hutool.core.collection.SpliteratorUtil;
import com.tomorrow.entity.Department;
import com.tomorrow.service.DepartmentService;
import com.tomorrow.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<Department> list(int limit){
        return departmentService.findAll(limit);
    }

    @RequestMapping("/depart")
    public String depart(){
        return "page/department";
    }

    @RequestMapping("/del")
    @ResponseBody
    public ReturnResult del(Department department){
        return departmentService.del(department.getDepartmentid());
    }

    @RequestMapping(value = "/findMsg",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Department> findMsg(String name){
        System.out.println("111111");
        System.out.println(name);
        System.out.println("222222");
        return departmentService.findMsg(name);

    }
    @RequestMapping("/view")
    public String view(){
        return "page/resultfind";
    }
}
