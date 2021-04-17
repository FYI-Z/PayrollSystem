package com.tomorrow.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tomorrow.entity.Salary;

import java.util.List;

public interface SalaryService {
    List<Salary> findSalaryByOne(String key, String value);
    List<Salary> findAllSalary();
    Salary findSalaryById(String salaryId);
    boolean updateSalary(Salary salary);
    boolean deleteSalary(String salaryId);
    boolean addSalary(Salary salary);
}
