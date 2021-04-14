package com.tomorrow.service;

import com.tomorrow.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    List<Department> findAll();
}
