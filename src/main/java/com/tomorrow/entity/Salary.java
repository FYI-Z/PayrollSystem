package com.tomorrow.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "salary")
public class Salary {

    @TableId(value = "salaryid")
    private String salaryid;

    @TableField(value = "userid")
    private String userid;

    @TableField(value = "salaryTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String salaryTime;

    @TableField(value = "basicSalary")
    private double basicSalary;

    @TableField(value = "overtime")
    private double overtime;

    @TableField(value = "commission")
    private double commission;

    @TableField(value = "bonus")
    private double bonus;

    @TableField(value = "vacate")
    private double vacate;

    @TableField(value = "late")
    private double late;

    @TableField(value = "absenteeism")
    private double absenteeism;

    @TableField(value = "actual")
    private double actual;

    @TableField(value = "salaryStatus")
    private String salaryStatus;

    @TableField(value = "applySalary")
    private double applySalary;

    @TableField(value = "applicant")
    private String applicant;

    @TableField(value = "applyStatus")
    private String applyStatus;
}
