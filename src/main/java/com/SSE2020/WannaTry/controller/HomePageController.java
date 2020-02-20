package com.SSE2020.WannaTry.controller;


import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/")
public class HomePageController {

    @GetMapping(value= "/")
    public String homePage(Model model){
        model.addAttribute("current_user",null);
        return "Home";
    }
    @GetMapping(value = "/Home")
    public String home(Model model){
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        return "Home";
    }

}
