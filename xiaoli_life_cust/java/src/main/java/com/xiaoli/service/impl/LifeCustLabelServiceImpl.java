package com.xiaoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoli.entity.LifeCustLabel;
import com.xiaoli.dao.LifeCustLabelMapper;
import com.xiaoli.service.LifeCustLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    @Override
    public List<LifeCustLabel> selectAll() {
        QueryWrapper<LifeCustLabel> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("exist",true);
        List<LifeCustLabel> list = lifeCustLabelMapper.selectList(queryWrapper);

        return list;
    }

    @Override
    public List<LifeCustLabel> selectAllByExist() {
        QueryWrapper<LifeCustLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("exist",true);
        List<LifeCustLabel> list = lifeCustLabelMapper.selectList(queryWrapper);

        return list;
    }

    @Override
    public Integer deleteCustLabelByID(Integer id) {
        QueryWrapper<LifeCustLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        LifeCustLabel lifeCustLabel = this.selectCustLabelByID(id);
        lifeCustLabel.setExist(false);
        lifeCustLabelMapper.updateById(lifeCustLabel);
        return 0;
    }

    @Override
    public LifeCustLabel selectCustLabelByID(Integer id) {
        QueryWrapper<LifeCustLabel> queryWrapper = new QueryWrapper<>();
        LifeCustLabel lifeCustLabel = lifeCustLabelMapper.selectById(id);
        return lifeCustLabel;
    }
}
