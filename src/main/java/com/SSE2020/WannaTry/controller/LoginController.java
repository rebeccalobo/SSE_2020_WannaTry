package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.model.Students;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping(value = "/Login")
    public String showLoginPage(Model model){
        Students student = new Students();
        model.addAttribute("user",student);
        return "Login";
    }
}
