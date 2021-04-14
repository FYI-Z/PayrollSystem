package com.tomorrow.service;

import com.tomorrow.entity.Department;
import com.tomorrow.vo.ReturnResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    List<Department> findAll();
    ReturnResult del(String departmentid);
}
