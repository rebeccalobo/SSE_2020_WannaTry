package com.SSE2020.WannaTry.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request){
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
