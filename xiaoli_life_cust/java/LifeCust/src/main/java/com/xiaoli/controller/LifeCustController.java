package com.xiaoli.controller;


import com.xiaoli.entity.LifeCust;
import com.xiaoli.entity.Result;
import com.xiaoli.service.LifeCustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lixingyu
 * @since 2025-04-03
 */
@Controller
@RequestMapping("/life-cust")
public class LifeCustController {

    @Autowired
    private LifeCustService lifeCustService;

    @PostMapping("/findAll")
    public Result<List<LifeCust>> findAll() {
        List<LifeCust> list = lifeCustService.selectAll();
        Result<List<LifeCust>> result = new Result<>();
        result.setData(list);
        result.setResultCode(200);
        result.setResultMsg("查询所有收支信息");
        return result;
    }

    @PostMapping("/gainListCustCount")
    public Result<Integer> gainListCustCount() {
        Integer count  = lifeCustService.gainListCustCount();
        Result<Integer> result = new Result<>();
        result.setData(count);
        result.setResultCode(200);
        result.setResultMsg("查询收支信息的条数");
        return result;
    }

    @PostMapping("/findByID")
    public Result<LifeCust> findByID(Integer id) {
        LifeCust lifeCust = lifeCustService.selectByID(id);
        Result<LifeCust> result = new Result<>();
        result.setData(lifeCust);
        result.setResultCode(200);
        result.setResultMsg("根据ID="+id +"查询的收支信息");
        return result;
    }

    @PostMapping("/gainLifeCustByFlag")
    public Result<Double> gainLifeCustByFlag(Integer flag){
        Double money = lifeCustService.gainLifeCustByFlag(flag);
        Result<Double> result = new Result<>();
        result.setData(money);
        result.setResultCode(200);
        result.setResultMsg("查询收支信息为：" + (flag == 0 ? "支出信息" : "收入信息") );
        return result;
    }

    @PostMapping("/addLifeCust")
    public Result<Integer> addLifeCust(LifeCust lifeCust) {
        Result<Integer> result = new Result<>();
        result.setResultCode(200);
        result.setResultMsg("添加新的收支信息");
        Integer res = lifeCustService.addLifeCust(lifeCust);
        result.setData(res);
        return result;
    }



}

