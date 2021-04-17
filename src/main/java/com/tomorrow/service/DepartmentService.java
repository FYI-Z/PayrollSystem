package com.tomorrow.service;

import com.tomorrow.entity.Department;
import com.tomorrow.vo.ReturnResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    List<Department> findAll(int count);

    ReturnResult del(String departmentid);

    List<Department> findResult(String name, String operator);

    ReturnResult add(Department department);

    ReturnResult update(String name, String Oname, String remark);

    List<Department> listEchart();
}
