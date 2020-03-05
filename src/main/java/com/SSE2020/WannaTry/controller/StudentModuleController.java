package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.databaserepo.ModuleRepository;
import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.TypedQuery;

@Controller
public class StudentModuleController {
    @Autowired
    BackendRepoService backendRepoService;
    @RequestMapping(value = "/StudentModule")
    public String goToModulePage(Model model) throws StudentNotFoundException {
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        return "StudentModule";
    }

}
