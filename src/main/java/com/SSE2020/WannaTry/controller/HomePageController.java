package com.SSE2020.WannaTry.controller;


import com.SSE2020.WannaTry.exceptions.UserNotFoundException;
import com.SSE2020.WannaTry.model.CustomUserDetails;
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
        model.addAttribute("flag", !(context instanceof CustomUserDetails));
//        ArrayList<String> genders = repoService.getUserRepository().getGenders();
//        HashMap<String,Integer> genderHash = new HashMap<>();
//        for(String s : genders){
//            if(genderHash.containsKey(s)){
//                genderHash.put(s,genderHash.get(s)+1);
//            }else{
//                genderHash.put(s,1);
//            }
//        }
       // model.addAttribute("genders",genderHash);
        return "Home";
    }
    @GetMapping(value = "/error")
    public String error(Model model){
        model.addAttribute("flag",true);
        return "redirect:/LoginFailed";
    }


}
