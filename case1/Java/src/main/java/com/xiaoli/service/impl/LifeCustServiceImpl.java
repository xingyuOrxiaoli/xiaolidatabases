package com.xiaoli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoli.entity.LifeCust;
import com.xiaoli.dao.LifeCustMapper;
import com.xiaoli.service.LifeCustLabelRelationService;
import com.xiaoli.service.LifeCustLabelService;
import com.xiaoli.service.LifeCustService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lixingyu
 * @since 2025-04-03
 */
@Service
public class LifeCustServiceImpl extends ServiceImpl<LifeCustMapper, LifeCust> implements LifeCustService {

    @Autowired
    private LifeCustMapper lifeCustMapper;

    @Autowired
    private LifeCustLabelService lifeCustLabelService;

    @Autowired
    private LifeCustLabelRelationService lifeCustLabelRelationService;


    @Override
    public List<LifeCust> selectAll() {
        List<LifeCust> list = null;
        list = lifeCustMapper.selectList(null);
        return list;
    }

    @Override
    public Integer gainListCustCount() {
        Integer count = 0;
        count = lifeCustMapper.selectCount(null);
        return count;
    }

    @Override
    public LifeCust selectByID(Integer id) {
        LifeCust lifeCust = lifeCustMapper.selectById(id);
        return lifeCust;
    }

    @Override
    public Double gainLifeCustByFlag(Integer flag) {
        Double money = 0.0;
        QueryWrapper<LifeCust> queryWrapper = new QueryWrapper<LifeCust>();
        queryWrapper.eq("flag", flag);
        queryWrapper.select("sum(money) as money");
        money = (Double) lifeCustMapper.selectMaps(queryWrapper).get(0).get("money");
        return money;
    }

    @Override
    public Integer addLifeCust(LifeCust lifeCust) {

        if(null == lifeCust.getNoteTime()) {
            lifeCust.setNoteTime(new Date(System.currentTimeMillis()));
        }
        AtomicReference<Integer> insert = new AtomicReference<>(lifeCustMapper.insert(lifeCust));
        lifeCust.getLabelIds().forEach(labelId -> {
            insert.updateAndGet(v -> v + lifeCustLabelRelationService.addLifeCustLabelRelation(lifeCust.getId(), labelId));
        });

        return insert.get();
    }
}
