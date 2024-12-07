package com.soa_.ManageFootballStadium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/admin")
    public String stadium() {
        return "stadium";
    }

    @GetMapping("/review")
    public String review() {
        return "review";
    }

    @GetMapping("/checkReview")
    public String checkReview() {
        return "checkreview";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin/chart")
    public String adminChart() {
        return "adminchart";
    }
}
