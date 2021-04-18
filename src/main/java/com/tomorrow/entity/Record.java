package com.tomorrow.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "record")
public class Record {
    @TableId(value = "recordid")
    private String recordid;
    @TableField(value = "userid")
    private String userid;
    @TableField(value = "reward")
    private double reward;
    @TableField(value = "punishment")
    private double punishment;
    @TableField(value = "recordTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String recordTime;
    @TableField(value = "status")
    private String status;
    @TableField(value = "examiner")
    private String examiner;
}
