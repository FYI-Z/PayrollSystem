package com.tomorrow.controller;

import cn.hutool.core.collection.SpliteratorUtil;
import com.tomorrow.entity.Department;
import com.tomorrow.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping()
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<Department> list(){
        return departmentService.findAll();
    }
    @RequestMapping(value = "/")
    public String depart(){
        return "department";
    }
}
