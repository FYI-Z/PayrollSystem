package com.tomorrow.service.imp;


import com.tomorrow.dao.DepartmentDao;
import com.tomorrow.entity.Department;
import com.tomorrow.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;
    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
