package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.service.CurrentStaffSingleton;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

    @RequestMapping(value="/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
}
