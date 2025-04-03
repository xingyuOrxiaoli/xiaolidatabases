package com.xiaoli.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

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

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
}

