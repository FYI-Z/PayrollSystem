package com.tomorrow.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "userinfo")
public class User {
    @TableId(value = "userid")
    public String userId;
    @TableField("password")
    public String password;
    @TableField("name")
    public String name;
    @TableField("sex")
    public String sex;
    @TableField("age")
    public int age;
    @TableField("department")
    public String department;
    @TableField("position")
    public String position;
    @TableField("phone")
    public String phone;
    @TableField("permission")
    public String permission;
    @TableField("status")
    public int status;
}
