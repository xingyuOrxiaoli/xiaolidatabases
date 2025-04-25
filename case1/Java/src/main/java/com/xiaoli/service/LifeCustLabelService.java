package com.xiaoli.service;

import com.xiaoli.entity.LifeCustLabel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lixingyu
 * @since 2025-04-03
 */
public interface LifeCustLabelService extends IService<LifeCustLabel> {


    List<LifeCustLabel> selectAll();

    List<LifeCustLabel> selectAllByExist();

    Integer deleteCustLabelByID(Integer id);

    LifeCustLabel selectCustLabelByID(Integer id);
}
