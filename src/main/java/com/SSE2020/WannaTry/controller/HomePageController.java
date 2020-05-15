package com.SSE2020.WannaTry.controller;


import com.SSE2020.WannaTry.exceptions.UserNotFoundException;
import com.SSE2020.WannaTry.model.CustomUserDetails;
import com.SSE2020.WannaTry.model.Role;
import com.SSE2020.WannaTry.model.User;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.NoSuchElementException;


@Controller
public class HomePageController {
    @Autowired
    BackendRepoService repoService;
    @GetMapping(value={ "/","/Home"})
    public String homePage(Model model){
        Object context = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(context instanceof CustomUserDetails){
            User user = ((CustomUserDetails) context).getUser();
            for(Role r : user.getRoles()){
                if(r.getName().equals("ROLE_STAFF")){
                    model.addAttribute("isStaff",true);
                    model.addAttribute("isStudent",false);
                    model.addAttribute("flag",false);
                }
                else if(r.getName().equals("ROLE_STUDENT")){
                    model.addAttribute("isStaff",false);
                    model.addAttribute("isStudent",true);
                    model.addAttribute("flag",false);
                }
            }
        }
        else{
            model.addAttribute("isStaff",false);
            model.addAttribute("isStudent",false);
            model.addAttribute("flag",true);
        }
        return "Home";
    }
    @GetMapping(value = "/error")
    public String error(Model model){
        model.addAttribute("flag",true);
        return "redirect:/LoginFailed";
    }


}
