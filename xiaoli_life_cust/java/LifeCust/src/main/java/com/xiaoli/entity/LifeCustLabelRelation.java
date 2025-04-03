package com.xiaoli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author linn
 * @since 2025-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LifeCustLabelRelation implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 映射表的ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * life_cust表的ID
     */
    @TableField("life_cust_id")
    private Integer lifeCustId;

    /**
     * life_cust_label表的ID
     */
    @TableField("life_cust_label_id")
    private Integer lifeCustLabelId;

    @TableField("createtime")
    private Date createtime;


}
