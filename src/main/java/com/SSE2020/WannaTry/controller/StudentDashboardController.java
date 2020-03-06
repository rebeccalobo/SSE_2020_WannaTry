package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentDashboardController {

    @RequestMapping(value = "/studentDashboard")
    public String goToDashboardPage(Model model) {
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        return "studentDashboard";
    }
}
