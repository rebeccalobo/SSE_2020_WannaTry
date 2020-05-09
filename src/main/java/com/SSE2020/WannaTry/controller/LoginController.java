package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.model.User;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    BackendRepoService repoService;
    @Autowired
    PasswordEncoder passwordEncoder;




    @RequestMapping(value = "/Login",method = RequestMethod.GET)
    public String showLoginPage(Model model){
        User user = new User();
        model.addAttribute("flag",true);
        model.addAttribute("user",user);
        return "Login";
    }

    @GetMapping(value = "/LoginSuccess")
    public String redirectToDashBoard(HttpSession session){
        session.setMaxInactiveInterval(120);
        return "redirect:/Dashboard";
    }

}

