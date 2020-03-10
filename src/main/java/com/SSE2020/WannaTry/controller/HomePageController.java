package com.SSE2020.WannaTry.controller;


import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentStaffSingleton;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
@RequestMapping(value = "/")
public class HomePageController {
    @Autowired
    BackendRepoService repoService;
    @GetMapping(value= "/")
    public String homePage(Model model){
        model.addAttribute("current_user",null);
        ArrayList<String> genders = repoService.getStudentRepo().getGenders();
        model.addAttribute("genders",genders);
        return "Home";
    }
    @GetMapping(value = "/Home")
    public String home(Model model){
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        model.addAttribute("current_staff", CurrentStaffSingleton.getInstance().getCurrentUser());
        ArrayList<String> genders = repoService.getStudentRepo().getGenders();
        model.addAttribute("genders",genders);
        return "Home";
    }

}
