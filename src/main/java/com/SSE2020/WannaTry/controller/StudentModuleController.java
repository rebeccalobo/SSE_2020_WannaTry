package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class StudentModuleController {
    @Autowired
    BackendRepoService backendRepoService;
    @RequestMapping(value = "/StudentModule")
    public String goToModulePage(Model model) throws StudentNotFoundException {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        ArrayList<Modules> modules = backendRepoService.getModuleRepo().getModules(CurrentUserSingleton.getInstance().getCurrentUser().getStudent_id());
        ArrayList<Modules> not_enorlled = backendRepoService.getModuleRepo().getAllModulesNotEnrolledNotExpired(CurrentUserSingleton.getInstance().getCurrentUser().getStudent_id(),date);
        model.addAttribute("modules",modules);
        model.addAttribute("available_modules",not_enorlled);
        return "StudentModule";
    }
    //DISPLAY ALL THE STUDENTS MODULE
    //DISPLAY AVAILABLE MODULES
}
