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
@TableName(value = "standard")
public class Standard {
    @TableField(value = "lateStandard")
    private double lateStandard;

    @TableField(value = "absenteeismStandard")
    private double absenteeismStandard;

    @TableField(value = "overtimeStandard")
    private double overtimeStandard;
}
