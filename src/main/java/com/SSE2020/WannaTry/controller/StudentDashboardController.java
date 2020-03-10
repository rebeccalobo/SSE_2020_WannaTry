package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class StudentDashboardController {
    @Autowired
    BackendRepoService backendRepoService;
    @RequestMapping(value = "/studentDashboard")
    public String goToDashboardPage(Model model) {
        ArrayList<Modules> modules = backendRepoService.getModuleRepo().getModules(CurrentUserSingleton.getInstance().getCurrentUser().getStudent_id());
        model.addAttribute("modules",modules);
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());


        return "studentDashboard";
    }
}
