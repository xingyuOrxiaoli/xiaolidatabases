package com.xiaoli.controller;


import com.xiaoli.entity.LifeCustLabel;
import com.xiaoli.entity.Result;
import com.xiaoli.service.LifeCustLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lixingyu
 * @since 2025-04-03
 */
@RestController
@RequestMapping("/life-cust-label")
public class LifeCustLabelController {

    @Autowired
    private LifeCustLabelService lifeCustLabelService;

    @GetMapping("/findAll")
    public Result<List<LifeCustLabel>> findAll(){
        List<LifeCustLabel> list  = lifeCustLabelService.selectAll();
        Result<List<LifeCustLabel>> result = new Result<>();
        result.setResultCode(200);
        result.setResultMsg("查询所有支出类别");
        result.setData(list);
        return result;
    }
    @GetMapping("/findAllByExist")
    public Result<List<LifeCustLabel>> findAllByExist(){
        List<LifeCustLabel> list  = lifeCustLabelService.selectAllByExist();
        Result<List<LifeCustLabel>> result = new Result<>();
        result.setResultCode(200);
        result.setResultMsg("查询所有存在支出类别");
        result.setData(list);
        return result;
    }
    @PostMapping("/deleteCustLabelByID")
    public Result<Integer> deleteCustLabelByID(Integer id){
        Integer delete = lifeCustLabelService.deleteCustLabelByID(id);
        Result<Integer> result = new Result<>();
        result.setData(delete);
        result.setResultMsg("伪删除花费类别 设置exist=false");
        result.setResultCode(200);
        return  result;
    }


}

