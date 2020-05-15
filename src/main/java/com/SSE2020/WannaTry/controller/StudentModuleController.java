package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.model.CustomUserDetails;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.model.User;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class StudentModuleController {

    @Autowired
    BackendRepoService backendRepoService;

    @RequestMapping(value = "/StudentModule")
    public String goToModulePage(Model model){
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();

        model.addAttribute("current_user", user);
        ArrayList<Modules> modules = backendRepoService.getModuleRepo().getModules(user.getID());
        ArrayList<Modules> modules_not_enrolled = backendRepoService.getModuleRepo().getAllModules();
        modules_not_enrolled.removeAll(modules);
        model.addAttribute("modules",modules);
        model.addAttribute("available_modules",modules_not_enrolled);
        model.addAttribute("isStaff",false);
        model.addAttribute("isStudent",true);
        model.addAttribute("flag",false);
        return "StudentModule";
    }
    //DISPLAY ALL THE STUDENTS MODULE
    //DISPLAY AVAILABLE MODULES
}
