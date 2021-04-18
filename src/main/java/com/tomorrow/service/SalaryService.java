package com.tomorrow.service;

import com.tomorrow.entity.Salary;

import java.util.List;

public interface SalaryService {

    boolean countSalary(String userId, double commission , double bonus);

    List<Salary> showSalary(String token, String userId);

    List<Salary> findSalaryByDep(String department);

    List<Salary> findSalaryByOne(String key, String value);

    List<Salary> findAllSalary();

    Salary findSalaryById(String salaryId);

    boolean updateSalary(Salary salary);

    boolean deleteSalary(String salaryId);

    boolean addSalary(Salary salary);
}