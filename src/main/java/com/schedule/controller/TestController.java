package com.schedule.controller;

import com.schedule.service.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @Autowired TestServiceImpl testService;

    @RequestMapping("/")
    public String test(Model model) {
        String testResult = testService.test();
        if (!testResult.isBlank() && !testResult.isEmpty()) {
            model.addAttribute("success", testResult);
        } else {
            model.addAttribute("fail", "fail to connect");
        }
        return "index";
    }
}