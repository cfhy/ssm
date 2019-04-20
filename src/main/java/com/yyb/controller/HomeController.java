package com.yyb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController{
    @RequestMapping(value = "/")
    public String index() {
        if (member == null) {
            return "redirect:login";
        }
        return "index";
    }

    @RequestMapping(value = "/index")
    public String home() {
        if (member == null) {
            return "redirect:login";
        }
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/bill")
    public String bill() {
        return "bill";
    }

    @RequestMapping(value = "/member")
    public String member() {
        return "member";
    }

    @RequestMapping(value = "/addBill")
    public String addBill() {
        return "addBill";
    }

    @RequestMapping(value = "/addMember")
    public String addMember() {
        return "addMember";
    }


}
