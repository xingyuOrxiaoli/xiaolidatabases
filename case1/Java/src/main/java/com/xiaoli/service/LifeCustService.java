package com.xiaoli.service;

import com.xiaoli.entity.LifeCust;
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
public interface LifeCustService extends IService<LifeCust> {

    List<LifeCust> selectAll();

    Integer gainListCustCount();

    LifeCust selectByID(Integer id);

    Double gainLifeCustByFlag(Integer flag);

    Integer addLifeCust(LifeCust lifeCust);
}
