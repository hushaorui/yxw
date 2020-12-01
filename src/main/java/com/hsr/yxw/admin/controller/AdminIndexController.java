package com.hsr.yxw.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminIndexController {

    @RequestMapping(value="admin/*")
    public String redirectIndex() {
        return "redirect:admin/admin-index";
    }
    @RequestMapping(value="admin/admin-index")
    public String index() {
        return "admin/admin-index";
    }

    /**
     * 主页
     */
    @RequestMapping(value = "admin/admin-content")
    public String content() {
        return "admin/admin-content";
    }
}