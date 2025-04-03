package com.xiaoli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/addIncome")
    public String addIncome() {
        return "addIncome";
    }

    @GetMapping("/addExpenses")
    public String addExpenses() {
        return "addExpenses";
    }

    @GetMapping("/incomeList")
    public String incomeList() {
        return "incomeList";
    }

    @GetMapping("/expensesList")
    public String expensesList() {
        return "expensesList";
    }

    @GetMapping("/addInterest")
    public String addInterest() {
        return "addInterest";
    }

//    @GetMapping("/")
//    public String welcome() {
//        return "welcome";
//    }
//
//    @GetMapping("/")
//    public String welcome() {
//        return "welcome";
//    }
//
//    @GetMapping("/")
//    public String welcome() {
//        return "welcome";
//    }
}
