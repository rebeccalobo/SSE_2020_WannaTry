package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.model.CustomUserDetails;
import com.SSE2020.WannaTry.model.Role;
import com.SSE2020.WannaTry.model.User;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class RegisterController {

    @Autowired
    BackendRepoService repoService;
    @Autowired
    PasswordEncoder passwordEncoder;



    @GetMapping(value="/Register")
    public String register(Model model){
        Object context = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("flag", !(context instanceof CustomUserDetails));
        User user = new User();
        model.addAttribute("User",user);
        model.addAttribute("incorrectFormat","correct");
        return "Register";
    }

    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String saveUser(HttpServletRequest request, @ModelAttribute("User")User user, Model model){
        ArrayList<Role> roles = new ArrayList<>();
        Role role = repoService.getRoleRepository().findByName("ROLE_STUDENT");
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setID(repoService.getUserRepository().getRandomID());
        repoService.getUserRepository().save(user);
        return "redirect:/Dashboard";
    }

    // Delete a Note
    @GetMapping("/unregister")
    public String deleteUser(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        repoService.getUserRepository().delete(user);
        return "redirect:/logout";
    }
}
