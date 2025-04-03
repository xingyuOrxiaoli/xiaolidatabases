package com.xiaoli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LifeCustLabel implements Serializable {


    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    @TableField(value = "life_cust_id")
    private Integer lifeCustId;
    @TableField(value = "life_cust_label_id")
    private Integer lifeCustLabelId;
    @TableField(value = "createtime")
    private Date createTime;
}
