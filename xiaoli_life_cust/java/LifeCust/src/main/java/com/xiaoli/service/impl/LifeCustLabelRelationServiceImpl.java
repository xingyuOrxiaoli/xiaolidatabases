package com.xiaoli.service.impl;

import com.xiaoli.entity.LifeCustLabel;
import com.xiaoli.entity.LifeCustLabelRelation;
import com.xiaoli.dao.LifeCustLabelRelationMapper;
import com.xiaoli.service.LifeCustLabelRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linn
 * @since 2025-04-03
 */
@Service
public class LifeCustLabelRelationServiceImpl extends ServiceImpl<LifeCustLabelRelationMapper, LifeCustLabelRelation> implements LifeCustLabelRelationService {

    @Autowired
    private LifeCustLabelRelationMapper lifeCustLabelRelationMapper;

    @Override
    public Integer addLifeCustLabelRelation(Integer lifeCustId, LifeCustLabel label) {
        LifeCustLabelRelation lifeCustLabelRelation = new LifeCustLabelRelation();
        lifeCustLabelRelation.setLifeCustId(lifeCustId);
        lifeCustLabelRelation.setLifeCustLabelId(label.getId());
        int insert = lifeCustLabelRelationMapper.insert(lifeCustLabelRelation);
        return insert;
    }
}
