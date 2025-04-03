package com.xiaoli.service.impl;

import com.xiaoli.entity.LifeCustLabel;
import com.xiaoli.dao.LifeCustLabelMapper;
import com.xiaoli.service.LifeCustLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lixingyu
 * @since 2025-04-03
 */
@Service
public class LifeCustLabelServiceImpl extends ServiceImpl<LifeCustLabelMapper, LifeCustLabel> implements LifeCustLabelService {

    @Autowired
    private LifeCustLabelMapper lifeCustLabelMapper;

}
