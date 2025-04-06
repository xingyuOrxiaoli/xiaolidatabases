package com.xiaoli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lixingyu
 * @since 2025-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LifeCust implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 时间
     */
    @TableField("note_time")
    private Date noteTime;

    /**
     * 简述
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 金额
     */
    @TableField("money")
    private Double money;

    /**
     * false表示支出 true表示收入
     */
    @TableField("flag")
    private Boolean flag;


    @TableField(exist = false)
    private List<LifeCust>  labels;

    @TableField(exist = false)
    private List<Integer>  labelIds;


}
