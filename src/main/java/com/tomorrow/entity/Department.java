package com.tomorrow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private String departmentid;
    private String name;
    private int number;
    private String operator;
    private String createtime;
    private int is_del;
    private int count;
}
