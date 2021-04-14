package com.tomorrow.controller;

import cn.hutool.core.collection.SpliteratorUtil;
import com.tomorrow.entity.Department;
import com.tomorrow.service.DepartmentService;
import com.tomorrow.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/findAll")
    @ResponseBody
    public List<Department> list(){
        return departmentService.findAll();
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
}
